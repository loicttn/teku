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

package tech.pegasys.teku.util.config;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Arrays.asList;

import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class NetworkDefinition {
  private static final ImmutableMap<String, NetworkDefinition> NETWORKS =
      ImmutableMap.<String, NetworkDefinition>builder()
          .put("minimal", builder().constants("minimal").startupTargetPeerCount(0).build())
          .put(
              "mainnet",
              builder()
                  .constants("mainnet")
                  .startupTimeoutSeconds(120)
                  .eth1DepositContractAddress("0x00000000219ab540356cBB839Cbe05303d7705Fa")
                  .build())
          .put(
              "medalla",
              builder()
                  .constants("medalla")
                  .initialStateFromClasspath("medalla-genesis.ssz")
                  .startupTimeoutSeconds(120)
                  .eth1DepositContractAddress("0x07b39F4fDE4A38bACe212b546dAc87C58DfE3fDC")
                  .discoveryBootnodes(
                      // PegaSys Teku
                      "enr:-KG4QFuKQ9eeXDTf8J4tBxFvs3QeMrr72mvS7qJgL9ieO6k9Rq5QuGqtGK4VlXMNHfe34Khhw427r7peSoIbGcN91fUDhGV0aDKQD8XYjwAAAAH__________4JpZIJ2NIJpcIQDhMExiXNlY3AyNTZrMaEDESplmV9c2k73v0DjxVXJ6__2bWyP-tK28_80lf7dUhqDdGNwgiMog3VkcIIjKA",

                      // Sigp Lighthouse
                      "enr:-LK4QKWk9yZo258PQouLshTOEEGWVHH7GhKwpYmB5tmKE4eHeSfman0PZvM2Rpp54RWgoOagAsOfKoXgZSbiCYzERWABh2F0dG5ldHOIAAAAAAAAAACEZXRoMpAAAAAAAAAAAAAAAAAAAAAAgmlkgnY0gmlwhDQlA5CJc2VjcDI1NmsxoQOYiWqrQtQksTEtS3qY6idxJE5wkm0t9wKqpzv2gCR21oN0Y3CCIyiDdWRwgiMo",
                      "enr:-LK4QEnIS-PIxxLCadJdnp83VXuJqgKvC9ZTIWaJpWqdKlUFCiup2sHxWihF9EYGlMrQLs0mq_2IyarhNq38eoaOHUoBh2F0dG5ldHOIAAAAAAAAAACEZXRoMpAAAAAAAAAAAAAAAAAAAAAAgmlkgnY0gmlwhA37LMaJc2VjcDI1NmsxoQJ7k0mKtTd_kdEq251flOjD1HKpqgMmIETDoD-Msy_O-4N0Y3CCIyiDdWRwgiMo",

                      // Prysmatic
                      "enr:-Ku4QLglCMIYAgHd51uFUqejD9DWGovHOseHQy7Od1SeZnHnQ3fSpE4_nbfVs8lsy8uF07ae7IgrOOUFU0NFvZp5D4wBh2F0dG5ldHOIAAAAAAAAAACEZXRoMpAYrkzLAAAAAf__________gmlkgnY0gmlwhBLf22SJc2VjcDI1NmsxoQJxCnE6v_x2ekgY_uoE1rtwzvGy40mq9eD66XfHPBWgIIN1ZHCCD6A",
                      "enr:-Ku4QOzU2MY51tYFcoByfULugCu2mepfqAbB0DajbRzg8xlILLfi5Iv_Wx-ARn8SiFoZZb3yp2x05cnUDYSoDYZupjIBh2F0dG5ldHOIAAAAAAAAAACEZXRoMpAYrkzLAAAAAf__________gmlkgnY0gmlwhBLf22SJc2VjcDI1NmsxoQLEq16KLm1vPjUKYGkHq296D60i7y209NYPUpwZPXDVgYN1ZHCCD6A",
                      "enr:-Ku4QOYFmi2BW_YPDew_CKdfMvsrcRY1ARA-ImtcqFl-lgoxOFbxte4PU44-1M3uRNSRM-6rVa8USGohmWwtgwalEt8Bh2F0dG5ldHOIAAAAAAAAAACEZXRoMpAYrkzLAAAAAf__________gmlkgnY0gmlwhBLf22SJc2VjcDI1NmsxoQKH3lxnglLqrA7L6sl5r7XFnckr3XCnlZMaBTYSdE8SHIN1ZHCCD6A",

                      // Proto
                      "enr:-Ku4QFVactU18ogiqPPasKs3jhUm5ISszUrUMK2c6SUPbGtANXVJ2wFapsKwVEVnVKxZ7Gsr9yEc4PYF-a14ahPa1q0Bh2F0dG5ldHOIAAAAAAAAAACEZXRoMpAYrkzLAAAAAf__________gmlkgnY0gmlwhGQbAHyJc2VjcDI1NmsxoQILF-Ya2i5yowVkQtlnZLjG0kqC4qtwmSk8ha7tKLuME4N1ZHCCIyg",

                      // Lighthouse v5.1
                      "enr:-LK4QCGFeQXjpQkgOfLHsbTjD65IOtSqV7Qo-Qdqv6SrL8lqFY7INPMMGP5uGKkVDcJkeXimSeNeypaZV3MHkcJgr9QCh2F0dG5ldHOIAAAAAAAAAACEZXRoMpDnp11aAAAAAf__________gmlkgnY0gmlwhA37LMaJc2VjcDI1NmsxoQJ7k0mKtTd_kdEq251flOjD1HKpqgMmIETDoD-Msy_O-4N0Y3CCIyiDdWRwgiMo",
                      "enr:-LK4QCpyWmMLYwC2umMJ_g0c9VY7YOFwZyaR80_tuQNTWOzJbaR82DDhVQYqmE_0gvN6Du5jwnxzIaaNRZQlVXzfIK0Dh2F0dG5ldHOIAAAAAAAAAACEZXRoMpDnp11aAAAAAf__________gmlkgnY0gmlwhCLR2xuJc2VjcDI1NmsxoQOYiWqrQtQksTEtS3qY6idxJE5wkm0t9wKqpzv2gCR21oN0Y3CCIyiDdWRwgiMo",

                      // Prysm v5.1
                      "enr:-Ku4QHWezvidY_m0dWEwERrNrqjEQWrlIx7b8K4EIxGgTrLmUxHCZPW5-t8PsS8nFxAJ8k8YacKP5zPRk5gbsTSsRTQBh2F0dG5ldHOIAAAAAAAAAACEZXRoMpAYrkzLAAAAAf__________gmlkgnY0gmlwhBLf22SJc2VjcDI1NmsxoQMypP_ODwTuBq2v0oIdjPGCEyu9Hb_jHDbuIX_iNvBRGoN1ZHCCGWQ",
                      "enr:-Ku4QOnVSyvzS3VbF87J8MubaRuTyfPi6B67XQg6-5eAV_uILAhn9geTTQmfqDIOcIeAxWHUUajQp6lYniAXPWncp6UBh2F0dG5ldHOIAAAAAAAAAACEZXRoMpAYrkzLAAAAAf__________gmlkgnY0gmlwhBLf22SJc2VjcDI1NmsxoQKekYKqUtwbaJKKCct_srE5-g7tBUm68mj_jpeSb7CCqYN1ZHCCC7g")
                  .build())
          .build();

  private final String constants;
  private final Optional<String> initialState;
  private final int startupTargetPeerCount;
  private final int startupTimeoutSeconds;
  private final List<String> discoveryBootnodes;
  private final Optional<Eth1Address> eth1DepositContractAddress;
  private final Optional<String> eth1Endpoint;

  private NetworkDefinition(
      final String constants,
      final Optional<String> initialState,
      final int startupTargetPeerCount,
      final int startupTimeoutSeconds,
      final List<String> discoveryBootnodes,
      final Optional<Eth1Address> eth1DepositContractAddress,
      final Optional<String> eth1Endpoint) {
    this.constants = constants;
    this.initialState = initialState;
    this.startupTargetPeerCount = startupTargetPeerCount;
    this.startupTimeoutSeconds = startupTimeoutSeconds;
    this.discoveryBootnodes = discoveryBootnodes;
    this.eth1DepositContractAddress = eth1DepositContractAddress;
    this.eth1Endpoint = eth1Endpoint;
  }

  public static NetworkDefinition fromCliArg(final String arg) {
    return NETWORKS.getOrDefault(arg.toLowerCase(Locale.US), builder().constants(arg).build());
  }

  private static Builder builder() {
    return new Builder();
  }

  public String getConstants() {
    return constants;
  }

  public Optional<String> getInitialState() {
    return initialState;
  }

  public Integer getStartupTargetPeerCount() {
    return startupTargetPeerCount;
  }

  public Integer getStartupTimeoutSeconds() {
    return startupTimeoutSeconds;
  }

  public List<String> getDiscoveryBootnodes() {
    return discoveryBootnodes;
  }

  public Optional<Eth1Address> getEth1DepositContractAddress() {
    return eth1DepositContractAddress;
  }

  public Optional<String> getEth1Endpoint() {
    return eth1Endpoint;
  }

  private static class Builder {
    private String constants;
    private Optional<String> initialState = Optional.empty();
    private int startupTargetPeerCount = Constants.DEFAULT_STARTUP_TARGET_PEER_COUNT;
    private int startupTimeoutSeconds = Constants.DEFAULT_STARTUP_TIMEOUT_SECONDS;
    private List<String> discoveryBootnodes = new ArrayList<>();
    private Optional<Eth1Address> eth1DepositContractAddress = Optional.empty();
    private Optional<String> eth1Endpoint = Optional.empty();

    public Builder constants(final String constants) {
      this.constants = constants;
      return this;
    }

    public Builder initialState(final String initialState) {
      this.initialState = Optional.of(initialState);
      return this;
    }

    public Builder initialStateFromClasspath(final String filename) {
      this.initialState =
          Optional.of(NetworkDefinition.class.getResource(filename).toExternalForm());
      return this;
    }

    public Builder startupTargetPeerCount(final int startupTargetPeerCount) {
      this.startupTargetPeerCount = startupTargetPeerCount;
      return this;
    }

    public Builder startupTimeoutSeconds(final int startupTimeoutSeconds) {
      this.startupTimeoutSeconds = startupTimeoutSeconds;
      return this;
    }

    public Builder discoveryBootnodes(final String... discoveryBootnodes) {
      this.discoveryBootnodes = asList(discoveryBootnodes);
      return this;
    }

    public Builder eth1DepositContractAddress(final String eth1Address) {
      this.eth1DepositContractAddress = Optional.of(Eth1Address.fromHexString(eth1Address));
      return this;
    }

    public Builder eth1Endpoint(final String eth1Endpoint) {
      this.eth1Endpoint = Optional.of(eth1Endpoint);
      return this;
    }

    public NetworkDefinition build() {
      checkNotNull(constants, "Missing constants");
      return new NetworkDefinition(
          constants,
          initialState,
          startupTargetPeerCount,
          startupTimeoutSeconds,
          discoveryBootnodes,
          eth1DepositContractAddress,
          eth1Endpoint);
    }
  }
}
