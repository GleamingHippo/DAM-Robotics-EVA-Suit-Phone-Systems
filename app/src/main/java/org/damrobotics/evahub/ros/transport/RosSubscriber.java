/*
Purpose: Receives ROS-compatible messages from the Zenoh network.

This is the counterpart to RosPublisher.

Inputs:
Zenoh session.
RosTopic.
RosQos.
Appropriate MessageCodec.
Callback to deliver received messages.

Outputs:
Decoded Java message objects.
Publication metadata, including sequence information and source timestamps.
Subscription errors.
 */

package org.damrobotics.evahub.ros.transport;

public class RosSubscriber {
}
