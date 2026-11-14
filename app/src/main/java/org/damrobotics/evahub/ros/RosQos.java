/*
Purpose: Handles ROS 2 Quality of Service compatibility.
Important because ROS 2 has QoS policies that affect whether messages are delivered and retained.

Inputs:
Reliability policy.
Durability policy.
History policy.
History depth.
Liveliness policy.

Outputs:
Corresponding Zenoh publisher/subscriber configuration.
The QoS representation required in ROS liveliness metadata.
Validation results when a requested policy isn't supported.
*/

package org.damrobotics.evahub.ros;

public class RosQos {
}
