# Detecting test smells with PMD

In folder [`pmd-documentation`](../pmd-documentation) you will find the documentation of a selection of PMD rules designed to catch test smells.
Identify which of the test smells discussed in classes are implemented by these rules.

Use one of the rules to detect a test smell in one of the following projects:

- [Apache Commons Collections](https://github.com/apache/commons-collections)
- [Apache Commons CLI](https://github.com/apache/commons-cli)
- [Apache Commons Math](https://github.com/apache/commons-math)
- [Apache Commons Lang](https://github.com/apache/commons-lang)

Discuss the test smell you found with the help of PMD and propose here an improvement.
Include the improved test code in this file.

## Answer
Correction
Ajouter ceci
```java @Test(expected = ConcurrentModificationException.class | NoSuchElementException.class)```
Supprimer le try catch de la methode
erreur
```java
    @Test
    public void testCollectionIteratorFailFast() {
        if (!isFailFastSupported()) {
            return;
        }

        if (isAddSupported()) {
            resetFull();
            final Iterator<E> iter0 = getCollection().iterator();
            final E o = getOtherElements()[0];
            getCollection().add(o);
            getConfirmed().add(o);
            assertThrows(ConcurrentModificationException.class, () -> iter0.next(),
                    "next after add should raise ConcurrentModification");
            verify();

            resetFull();
            final Iterator<E> iter = getCollection().iterator();
            getCollection().addAll(Arrays.asList(getOtherElements()));
            getConfirmed().addAll(Arrays.asList(getOtherElements()));
            assertThrows(ConcurrentModificationException.class, () -> iter.next(),
                    "next after addAll should raise ConcurrentModification");
            verify();
        }

        if (!isRemoveSupported()) {
            return;
        }

        resetFull();
        try {
            final Iterator<E> iter = getCollection().iterator();
            getCollection().clear();
            iter.next();
            fail("next after clear should raise ConcurrentModification");
        } catch (final ConcurrentModificationException | NoSuchElementException e) {
            // ConcurrentModificationException: expected
            // NoSuchElementException: (also legal given spec)
        }

        resetFull();
        final Iterator<E> iter0 = getCollection().iterator();
        getCollection().remove(getFullElements()[0]);
        assertThrows(ConcurrentModificationException.class, () -> iter0.next(),
                "next after remove should raise ConcurrentModification");

        resetFull();
        final Iterator<E> iter1 = getCollection().iterator();
        getCollection().removeIf(e -> false);
        assertThrows(ConcurrentModificationException.class, () -> iter1.next(),
                "next after removeIf should raise ConcurrentModification");

        resetFull();
        final Iterator<E> iter2 = getCollection().iterator();
        final List<E> sublist = Arrays.asList(getFullElements()).subList(2, 5);
        getCollection().removeAll(sublist);
        assertThrows(ConcurrentModificationException.class, () -> iter2.next(),
                "next after removeAll should raise ConcurrentModification");

        resetFull();
        final Iterator<E> iter3 = getCollection().iterator();
        final List<E> sublist3 = Arrays.asList(getFullElements()).subList(2, 5);
        getCollection().retainAll(sublist3);
        assertThrows(ConcurrentModificationException.class, () -> iter3.next(),
                "next after retainAll should raise ConcurrentModification");
    }```
