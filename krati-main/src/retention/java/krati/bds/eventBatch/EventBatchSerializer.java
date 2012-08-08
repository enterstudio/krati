/*
 * Copyright (c) 2010-2012 LinkedIn, Inc
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package krati.bds.eventBatch;

import krati.bds.marker.AdvanceMarker;
import krati.io.SerializationException;
import krati.io.Serializer;

/**
 * EventBatchSerializer
 * 
 * @version 0.4.2
 * @author jwu
 * 
 * <p>
 * 07/31, 2011 - Created
 */
public interface EventBatchSerializer<T, A extends AdvanceMarker> extends Serializer<EventBatch<T, A>> {
    
    public EventBatchHeader<A> deserializeHeader(byte[] bytes) throws SerializationException;
    
    public byte[] serializeHeader(EventBatchHeader<A> header) throws SerializationException;
}