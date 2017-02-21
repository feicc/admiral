/*
 * Copyright (c) 2017 VMware, Inc. All Rights Reserved.
 *
 * This product is licensed to you under the Apache License, Version 2.0 (the "License").
 * You may not use this product except in compliance with the License.
 *
 * This product may include a number of subcomponents with separate copyright notices
 * and license terms. Your use of these subcomponents is subject to the terms and
 * conditions of the subcomponent's license, as noted in the LICENSE file.
 */

package com.vmware.admiral.compute.kubernetes.service;

import com.vmware.admiral.common.ManagementUriParts;
import com.vmware.admiral.compute.kubernetes.entities.replicationcontrollers.ReplicationController;
import com.vmware.admiral.compute.kubernetes.service.ReplicationControllerService.ReplicationControllerState;

public class ReplicationControllerService
        extends AbstractKubernetesObjectService<ReplicationControllerState> {

    public static final String FACTORY_LINK = ManagementUriParts.KUBERNETES_REPLICATION_CONTROLLERS;

    public static class ReplicationControllerState extends BaseKubernetesState {

        /**
         * ReplicationController represents the configuration of a replication controller.
         */
        @Documentation(
                description = "ReplicationController represents the configuration of a replication controller.")
        public ReplicationController replicationController;

        @Override
        public String getKubernetesSelfLink() {
            return this.replicationController.metadata.selfLink;
        }
    }

    public ReplicationControllerService() {
        super(ReplicationControllerState.class);
    }
}
