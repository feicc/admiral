/*
 * Copyright (c) 2016 VMware, Inc. All Rights Reserved.
 *
 * This product is licensed to you under the Apache License, Version 2.0 (the "License").
 * You may not use this product except in compliance with the License.
 *
 * This product may include a number of subcomponents with separate copyright notices
 * and license terms. Your use of these subcomponents is subject to the terms and
 * conditions of the subcomponent's license, as noted in the LICENSE file.
 */

package com.vmware.admiral.request;

import com.vmware.admiral.common.ManagementUriParts;
import com.vmware.admiral.common.util.OperationUtil;
import com.vmware.admiral.request.RequestStatusService.RequestStatus;
import com.vmware.xenon.common.FactoryService;
import com.vmware.xenon.common.Operation;
import com.vmware.xenon.common.Service;

/**
 * Factory service implementing {@link FactoryService} used to create instances of
 * {@link RequestStatusService}.
 */
public class RequestStatusFactoryService extends FactoryService {
    public static final String SELF_LINK = ManagementUriParts.REQUEST_STATUS;

    public RequestStatusFactoryService() {
        super(RequestStatus.class);
        super.toggleOption(ServiceOption.PERSISTENCE, true);
        super.toggleOption(ServiceOption.REPLICATION, true);
        super.toggleOption(ServiceOption.INSTRUMENTATION, true);
        super.toggleOption(ServiceOption.IDEMPOTENT_POST, true);
    }

    @Override
    public Service createServiceInstance() throws Throwable {
        return new RequestStatusService();
    }

    @Override
    public void handleGet(Operation get) {
        OperationUtil.transformProjectHeaderToFilterQuery(get);
        super.handleGet(get);
    }
}
