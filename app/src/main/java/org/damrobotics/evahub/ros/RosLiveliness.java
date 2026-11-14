/*
Purpose: Make EVAHub's ROS entities discoverable to other ROS 2 participants.
Simply publishing ROS-compatible data doesn't make EVAHub visible in the ROS graph.
The Jazzy implementation uses Zenoh liveliness tokens to represent nodes, publishers, subscriptions, and other entities.

Inputs:
RosNodeIdentity.
RosTopic.
Entity type.
QoS metadata.

Outputs:
Declared liveliness tokens.
Notifications when remote entities appear or disappear.
Information usable for ROS graph introspection.

Identifier	Meaning
NN	        ROS node
MP	        Message publisher
MS	        Message subscription
SS	        Service server
SC	        Service client

For our initial project focus on, NN, MP, and MS

*/

package org.damrobotics.evahub.ros;

public class RosLiveliness {
}
