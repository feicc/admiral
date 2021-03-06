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

import PlacementZonesViewVue from 'components/placementzones/PlacementZonesViewVue.html';
import PlacementZoneEditor from 'components/placementzones/PlacementZoneEditor'; //eslint-disable-line
import { PlacementZonesActions } from 'actions/Actions';
import utils from 'core/utils';

var PlacementZonesView = Vue.extend({
  template: PlacementZonesViewVue,
  props: {
    model: {
      required: true,
      type: Object,
      default: () => {
        return {
          items: {}
        };
      }
    }
  },
  data: function() {
    var sortOrders = {
      name: 1,
      cpuPercentage: 1,
      memoryPercentage: 1,
      hostsCount: 1
    };
    return {
      deleteConfirmationItem: null,
      sortKey: '',
      sortOrders: sortOrders
    };
  },
  computed: {
    itemsCount: function() {
      var items = this.model.items;

      return items ? Object.keys(items).length : 0;
    },
    isDeleteConfirmationLoading: function() {
      return this.model.deleteConfirmationLoading;
    }
  },
  methods: {
    getDocumentId: function(item) {
      return utils.getDocumentId(item.resourcePoolState.documentSelfLink);
    },
    getHostsPath: function() {
      if (utils.isApplicationCompute()) {
        return 'compute';
      } else {
        return 'hosts';
      }
    },
    getPercentageLevel: function(percentage) {
      if (percentage < 50) {
        return 'success';
      } else if (percentage < 80) {
        return 'warning';
      } else {
        return 'danger';
      }
    },
    isHighlightedItem: function(item) {
      return this.isNewItem(item) || this.isUpdatedItem(item);
    },
    isNewItem: function(item) {
      return item === this.model.newItem;
    },
    isUpdatedItem: function(item) {
      return item === this.model.updatedItem;
    },
    isEditingItem: function(item) {
      var editingItem = this.model.editingItemData && this.model.editingItemData.item;
      return editingItem && editingItem.documentSelfLink === item.documentSelfLink;
    },
    isEditingNewItem: function() {
      var editingItem = this.model.editingItemData && this.model.editingItemData.item;
      return editingItem && !editingItem.documentSelfLink;
    },
    isEditingOrHighlightedItem: function(item) {
      return this.isEditingItem(item) || this.isHighlightedItem(item);
    },
    isDeleting: function(item) {
      return this.deleteConfirmationItem === item;
    },
    addNewItem: function($event) {
      $event.stopImmediatePropagation();
      $event.preventDefault();

      PlacementZonesActions.editPlacementZone({});
    },
    editItem: function(item, $event) {
      $event.stopImmediatePropagation();
      $event.preventDefault();

      PlacementZonesActions.editPlacementZone(item);
    },
    confirmDelete: function(item, $event) {
      $event.stopImmediatePropagation();
      $event.preventDefault();

      this.deleteConfirmationItem = item;
    },
    cancelDelete: function($event) {
      $event.stopImmediatePropagation();
      $event.preventDefault();

      this.deleteConfirmationItem = null;
    },
    deleteItem: function($event) {
      $event.stopImmediatePropagation();
      $event.preventDefault();

      PlacementZonesActions.deletePlacementZone(this.deleteConfirmationItem);
    },
    sortBy: function(key) {
      this.sortKey = key;
      this.sortOrders[key] = this.sortOrders[key] * -1;
    },
    refresh: function() {
      PlacementZonesActions.retrievePlacementZones();
    }
  },
  filters: {
    orderBy: function(items, sortKey, reverse) {
      if (!sortKey) {
        return items;
      }
      var order = reverse && reverse < 0 ? -1 : 1;
      return items.asMutable().sort(function(a, b) {
        a = String(a[sortKey] || '');
        b = String(b[sortKey] || '');
        return a.toLowerCase().localeCompare(b.toLowerCase()) * order;
      });
    }
  }
});

Vue.component('placement-zones-view', PlacementZonesView);

export default PlacementZonesView;
