// Copyright (C) 2018 GerritForge Ltd
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.googlesource.gerrit.plugins.account;

import static com.google.gerrit.server.account.AccountResource.ACCOUNT_KIND;

import com.google.gerrit.extensions.annotations.Exports;
import com.google.gerrit.extensions.config.CapabilityDefinition;
import com.google.gerrit.extensions.restapi.RestApiModule;
import com.google.inject.AbstractModule;
import com.googlesource.gerrit.plugins.account.permissions.DeleteAccountCapability;
import com.googlesource.gerrit.plugins.account.permissions.DeleteOwnAccountCapability;

class Module extends AbstractModule {

  @Override
  protected void configure() {
    install(
        new RestApiModule() {
          @Override
          protected void configure() {
            delete(ACCOUNT_KIND).to(DeleteAccount.class);
          }
        });

    bind(CapabilityDefinition.class)
        .annotatedWith(Exports.named(DeleteAccountCapability.DELETE_ACCOUNT))
        .to(DeleteAccountCapability.class);
    bind(CapabilityDefinition.class)
        .annotatedWith(Exports.named(DeleteOwnAccountCapability.DELETE_OWN_ACCOUNT))
        .to(DeleteOwnAccountCapability.class);
  }
}
