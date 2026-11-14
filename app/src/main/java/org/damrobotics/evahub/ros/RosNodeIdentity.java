/*
Purpose: Holds the identity of EVAHub as a ROS participant.
ROS 2 needs to distinguish different nodes and their entities.

Inputs:
Node name.
Node namespace.
ROS domain ID.
Enclave information, if security is used.
Zenoh session identity.

Outputs:
Unique node identity.
Entity identifiers.
Publisher GIDs and other identity information required for ROS interoperability.
*/

package org.damrobotics.evahub.ros;

public class RosNodeIdentity {
}
