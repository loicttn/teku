/*
 * Copyright 2020 ConsenSys AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package tech.pegasys.teku.validator.client;

import tech.pegasys.teku.service.serviceutils.layout.DataConfig;
import tech.pegasys.teku.util.config.GlobalConfiguration;
import tech.pegasys.teku.validator.api.ValidatorConfig;

public class ValidatorClientConfiguration {
  private final GlobalConfiguration globalConfiguration;
  private final ValidatorConfig validatorConfig;
  private final DataConfig dataConfig;

  public ValidatorClientConfiguration(
      final GlobalConfiguration globalConfiguration,
      final ValidatorConfig validatorConfig,
      final DataConfig dataConfig) {
    this.globalConfiguration = globalConfiguration;
    this.validatorConfig = validatorConfig;
    this.dataConfig = dataConfig;
  }

  public GlobalConfiguration getGlobalConfiguration() {
    return globalConfiguration;
  }

  public ValidatorConfig getValidatorConfig() {
    return validatorConfig;
  }

  public DataConfig getDataConfig() {
    return dataConfig;
  }
}
