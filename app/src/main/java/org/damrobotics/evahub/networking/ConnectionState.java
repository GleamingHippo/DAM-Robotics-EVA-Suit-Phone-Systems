/*
Purpose: Defines the application's networking state.

Inputs: Connection lifecycle events.

Outputs: A standardized connection state that other application components can inspect.
*/

package org.damrobotics.evahub.networking;

public enum ConnectionState {
        DISCONNECTED,
        CONNECTING,
        CONNECTED,
        RECONNECTING,
        FAILED,
        CLOSING
}
