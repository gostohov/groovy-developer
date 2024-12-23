package org.example

import org.junit.Test
import static org.junit.Assert.*

class DslTest {

    @Test
    void testSimpleConfig() {
        def config = WebServerDSL.configure {
            server {
                host 'testhost'
                port 1234
            }
        }
        assertEquals('testhost', config.host)
        assertEquals(1234, config.port)
    }
}