/*
 * Copyright 2015-present Open Networking Foundation
 * Copyright © 2020 camunda services GmbH (info@camunda.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.atomix.storage.journal;

import static com.google.common.base.MoreObjects.toStringHelper;

import io.atomix.storage.protocol.EntryType;
import java.util.Objects;
import org.agrona.DirectBuffer;

/** Stores a state change in a RaftLog. */
public class RaftLogEntry {

  private final long term;
  private final long timestamp;
  private final EntryType entryType;
  private final DirectBuffer entry;

  public RaftLogEntry(
      final long term, final long timestamp, final EntryType entryType, final DirectBuffer entry) {
    this.term = term;
    this.timestamp = timestamp;
    this.entryType = entryType;
    this.entry = entry;
  }

  /**
   * Returns the entry term.
   *
   * @return The entry term.
   */
  public long term() {
    return term;
  }

  public long timestamp() {
    return timestamp;
  }

  public EntryType type() {
    return entryType;
  }

  public DirectBuffer entry() {
    return entry;
  }

  @Override
  public int hashCode() {
    return Objects.hash(term, timestamp, entryType, entry);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final RaftLogEntry that = (RaftLogEntry) o;
    return term == that.term
        && timestamp == that.timestamp
        && entryType == that.entryType
        && entry.equals(that.entry);
  }

  @Override
  public String toString() {
    return toStringHelper(this).add("term", term).toString();
  }
}