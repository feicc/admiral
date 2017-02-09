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

package com.vmware.admiral.test.integration.client;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.vmware.admiral.test.integration.client.dcp.DcpDocumentKind;

/**
 * ComputeDescription
 *
 * TODO: should be tenanted.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@DcpDocumentKind("com:vmware:photon:controller:model:resources:ComputeDescriptionService:ComputeDescription")
public class ComputeDescription extends TenantedServiceDocument {
    public static final String RESOURCE_TYPE = "COMPUTE";

    public static enum ComputeType {
        VM_HOST, VM_GUEST, DOCKER_CONTAINER, PHYSICAL, OS_ON_PHYSICAL
    }

    /** (Mandatory) id of ComputeDescription */
    public String id;

    /**
     * Name of this description service instance.
     */
    public String name;

    /** The list of machine types this host supports actuating. */
    public List<String> supportedChildren;

    public long cpuCount;
    public long cpuMhzPerCore;

    public long gpuCount;

    public long totalMemoryBytes;

    public URI instanceAdapterReference;
    public URI powerAdapterReference;
    public URI bootAdapterReference;
    public URI healthAdapterReference;
    public URI enumerationAdapterReference;

    public String authCredentialsLink;

    public String environmentName;
    public String dataStoreId;
    public String networkId;

    public String regionId;
    public String zoneId;
    public String instanceType;

    public double costPerMinute;
    public String currencyUnit;

    public Map<String, String> customProperties;

    public String getCustomProperty(String key) {
        if (customProperties == null) {
            return null;
        }
        Object value = customProperties.get(key);
        if (value == null) {
            return null;
        }
        return value.toString();
    }

    public void setCustomProperty(String key, String value) {
        if (customProperties == null) {
            customProperties = new HashMap<>(2);
        }
        customProperties.put(key, value);
    }

}