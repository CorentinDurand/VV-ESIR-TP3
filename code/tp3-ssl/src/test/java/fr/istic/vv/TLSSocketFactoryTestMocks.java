package fr.istic.vv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TLSSocketFactoryTestMocks {

    private TLSSocketFactory tlsSocketFactory;
    private SSLSocket sslSocket;

    @BeforeEach
    public void setUp() {
        tlsSocketFactory = new TLSSocketFactory();
        sslSocket = Mockito.mock(SSLSocket.class);  
    }

    @Test
    public void preparedSocket_NullProtocols() {
        // Mock SSLSocket to return null for both supported and enabled protocols
        when(sslSocket.getSupportedProtocols()).thenReturn(null);
        when(sslSocket.getEnabledProtocols()).thenReturn(null);

        tlsSocketFactory.prepareSocket(sslSocket);

        // Verify that setEnabledProtocols is not called
        verify(sslSocket, never()).setEnabledProtocols(any(String[].class));
    }

    @Test
    public void typical() {
        // Mock supported and enabled protocols
        when(sslSocket.getSupportedProtocols())
                .thenReturn(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"});
        when(sslSocket.getEnabledProtocols())
                .thenReturn(new String[]{"SSLv3", "TLSv1"});

        tlsSocketFactory.prepareSocket(sslSocket);

        // Verify that setEnabledProtocols is called with the correct protocols in the expected order
        verify(sslSocket).setEnabledProtocols(new String[]{"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"});
    }

    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<String>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }
}
