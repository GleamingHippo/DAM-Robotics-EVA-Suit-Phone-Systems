/*

File Purpose: Own and manage Zenoh session. The lowest-level networking component.
It establishes the connection between the Android phone and your existing Zenoh infrastructure.

Inputs:
Router endpoint (IP address and port).
Zenoh session configuration.
Optional credentials or security configuration.

Outputs:
An active Zenoh session, exposed internally to the ROS transport components.
Connection errors.
Session lifecycle events.

Responsibilities:
Open Zenoh session.
Close sessions safely.
Provide access to the active session.
Detect invalid or closed sessions.
Prevent multiple accidental sessions.
Release all associated networking resources.

*/

//~~~~~~~~~~~~~~~~~~~CURRENT STATE: Should be good I guess~~~~~~~~~~~~~~~~~~~

package org.damrobotics.evahub.networking;

import io.zenoh.Config;
import io.zenoh.Session;
import io.zenoh.Zenoh;

public final class ZenohManager implements AutoCloseable {

    private Session session;

    public synchronized boolean isConnected() {
        return session != null && !session.isClosed();
    }

    public synchronized void connect(Config config) throws Exception {
        //Check whether a session is already connected.
        if (isConnected()) {
            throw new IllegalStateException("Zenoh session is already connected!");
        }
        //Open a new session using Zenoh.INSTANCE.open(config).
        this.session = Zenoh.INSTANCE.open(config);
    }

    public synchronized Session getSession() {
        if (!isConnected()) {
            throw new IllegalStateException(
                    "Cannot access Zenoh session: not connected!"
            );
        }
        return this.session;
    }

    @Override

    public synchronized void close() {

        if (session == null) {
            return;
        }

        try {
            if (!session.isClosed()) {
                session.close();
            }
        }

        finally {
            session = null;
        }
    }
}