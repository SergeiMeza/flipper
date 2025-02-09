/*
 * Copyright (c) Meta Platforms, Inc. and affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.facebook.flipper.plugins.uidebugger.litho.descriptors

import com.facebook.flipper.plugins.uidebugger.common.Inspectable
import com.facebook.flipper.plugins.uidebugger.common.InspectableObject
import com.facebook.flipper.plugins.uidebugger.common.InspectableValue
import com.facebook.flipper.plugins.uidebugger.descriptors.ChainedDescriptor
import com.facebook.flipper.plugins.uidebugger.descriptors.SectionName
import com.facebook.litho.DebugComponent
import com.facebook.litho.LithoView

object LithoViewDescriptor : ChainedDescriptor<LithoView>() {

  override fun onGetName(node: LithoView): String = node.javaClass.simpleName

  override fun onGetChildren(node: LithoView): List<Any>? {
    val result = mutableListOf<Any>()
    val debugComponent = DebugComponent.getRootInstance(node)
    if (debugComponent != null) {
      result.add(debugComponent)
    }
    return result
  }

  override fun onGetData(
      node: LithoView,
      attributeSections: MutableMap<SectionName, InspectableObject>
  ) {
    attributeSections["Litho"] =
        InspectableObject(
            mapOf<String, Inspectable>(
                "isIncrementalMountEnabled" to
                    InspectableValue.Boolean(node.isIncrementalMountEnabled, false)))
  }
}
