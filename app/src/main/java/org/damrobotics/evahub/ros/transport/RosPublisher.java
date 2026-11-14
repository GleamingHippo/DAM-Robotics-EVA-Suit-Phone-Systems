/*
Purpose: Publishes ROS-compatible messages through Zenoh.

Inputs:
Zenoh session.
RosTopic.
RosQos.
MessageCodec.
RosNodeIdentity.
Java message objects to publish.

Outputs:
Published ROS-compatible payloads.
Publication metadata.
Errors if serialization or transmission fails.

Java ROS message
\/
CdrSerializer
(Produce CDR bytes)
\/
RmwAttachment
(Create publication metadata)
\/
Zenoh Publisher
(Publishe using the ROS key expression)


 */

package org.damrobotics.evahub.ros.transport;

public class RosPublisher {
}
