/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.stream.kstream;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.processor.ProcessorSupplier;
import org.apache.kafka.streams.processor.StreamPartitioner;

/**
 * @author Marius Bogoevici
 */
public class KStreamDelegate<K, V> implements KStream<K, V> {

	private volatile KStream<K, V> delegate;

	public void setDelegate(KStream<K, V> delegate) {
		this.delegate = delegate;
	}

	@Override
	public KStream<K, V> filter(Predicate<? super K, ? super V> predicate) {
		return delegate.filter(predicate);
	}

	@Override
	public KStream<K, V> filterNot(Predicate<? super K, ? super V> predicate) {
		return delegate.filterNot(predicate);
	}

	@Override
	public <KR> KStream<KR, V>  selectKey(KeyValueMapper<? super K, ? super V, ? extends KR> mapper) {
		return delegate.selectKey(mapper);
	}

	@Override
	public <KR, VR> KStream<KR, VR> map(KeyValueMapper<? super K, ? super V, ? extends KeyValue<? extends KR, ? extends VR>> mapper) {
		return delegate.map(mapper);
	}

	@Override
	public <VR> KStream<K, VR> mapValues(ValueMapper<? super V, ? extends VR> mapper)  {
		return delegate.mapValues(mapper);
	}

	@Override
	public void print() {
		delegate.print();
	}

	@Override
	public void print(String streamName) {
		delegate.print(streamName);
	}

	@Override
	public void print(Serde<K> keySerde, Serde<V> valSerde) {
		delegate.print(keySerde, valSerde);
	}

	@Override
	public void print(Serde<K> keySerde, Serde<V> valSerde, String streamName) {
		delegate.print(keySerde, valSerde, streamName);
	}

	@Override
	public void writeAsText(String filePath) {
		delegate.writeAsText(filePath);
	}

	@Override
	public void writeAsText(String filePath, String streamName) {
		delegate.writeAsText(filePath, streamName);
	}

	@Override
	public void writeAsText(String filePath, Serde<K> keySerde, Serde<V> valSerde) {
		delegate.writeAsText(filePath, keySerde, valSerde);
	}

	@Override
	public void writeAsText(String filePath, String streamName, Serde<K> keySerde, Serde<V> valSerde) {
		delegate.writeAsText(filePath, streamName, keySerde, valSerde);
	}

	@Override
	public <KR, VR> KStream<KR, VR> flatMap(KeyValueMapper<? super K, ? super V, ? extends Iterable<? extends KeyValue<? extends KR, ? extends VR>>> mapper) {
		return delegate.flatMap(mapper);
	}

	@Override
	public <VR> KStream<K, VR> flatMapValues(ValueMapper<? super V, ? extends Iterable<? extends VR>> processor) {
		return delegate.flatMapValues(processor);
	}

	@Override
	public KStream<K, V>[] branch(Predicate<? super K, ? super V>... predicates) {
		return delegate.branch(predicates);
	}

	@Override
	public KStream<K, V> through(String topic) {
		return delegate.through(topic);
	}

	@Override
	public void foreach(ForeachAction<? super K, ? super V> action) {
		delegate.foreach(action);
	}

	@Override
	public KStream<K, V> through(StreamPartitioner<? super K, ? super V> partitioner, String topic) {
		return delegate.through(partitioner, topic);
	}

	@Override
	public KStream<K, V> through(Serde<K> keySerde, Serde<V> valSerde, String topic) {
		return delegate.through(keySerde, valSerde, topic);
	}

	@Override
	public KStream<K, V> through(Serde<K> keySerde, Serde<V> valSerde, StreamPartitioner<? super K, ? super V> partitioner, String topic) {
		return delegate.through(keySerde, valSerde, partitioner, topic);
	}

	@Override
	public void to(String topic) {
		delegate.to(topic);
	}

	@Override
	public void to(StreamPartitioner<? super K, ? super V> partitioner, String topic) {
		delegate.to(partitioner, topic);		
	}

	@Override
	public void to(Serde<K> keySerde, Serde<V> valSerde, String topic) {
		delegate.to(keySerde, valSerde, topic);
	}

	@Override
	public void to(Serde<K> keySerde, Serde<V> valSerde, StreamPartitioner<? super K, ? super V> partitioner, String topic) {
		delegate.to(keySerde, valSerde, partitioner, topic);
	}

	@Override
	public <K1, V1> KStream<K1, V1> transform(TransformerSupplier<? super K, ? super V, KeyValue<K1, V1>> transformerSupplier, String... stateStoreNames) {
		return delegate.transform(transformerSupplier, stateStoreNames);
	}

	@Override
	public <VR> KStream<K, VR> transformValues(ValueTransformerSupplier<? super V, ? extends VR> valueTransformerSupplier, String... stateStoreNames) {
		return delegate.transformValues(valueTransformerSupplier, stateStoreNames);
	}

	@Override
	public void process(ProcessorSupplier<? super K, ? super V> processorSupplier, String... stateStoreNames) {
		delegate.process(processorSupplier, stateStoreNames);
	}

	@Override
	public <VO, VR> KStream<K, VR> join(KStream<K, VO> otherStream, ValueJoiner<? super V, ? super VO, ? extends VR> joiner, JoinWindows windows, Serde<K> keySerde, Serde<V> thisValueSerde, Serde<VO> otherValueSerde) {
		return delegate.join(otherStream, joiner, windows, keySerde, thisValueSerde, otherValueSerde);
	}

	@Override
	public <VO, VR> KStream<K, VR> join(KStream<K, VO> otherStream, ValueJoiner<? super V, ? super VO, ? extends VR> joiner, JoinWindows windows) {
		return delegate.join(otherStream, joiner, windows);
	}

	@Override
	public <GK, GV, RV> KStream<K, RV> join(GlobalKTable<GK, GV> globalKTable, KeyValueMapper<? super K, ? super V, ? extends GK> keyValueMapper, ValueJoiner<? super V, ? super GV, ? extends RV> joiner) {
		return delegate.join(globalKTable, keyValueMapper, joiner);
	}

	@Override
	public <VT, VR> KStream<K, VR> join(KTable<K, VT> table, ValueJoiner<? super V, ? super VT, ? extends VR> joiner) {
		return delegate.join(table, joiner);
	}

	@Override
	public <VT, VR> KStream<K, VR> join(KTable<K, VT> table, ValueJoiner<? super V, ? super VT, ? extends VR> joiner, Serde<K> keySerde, Serde<V> valSerde) {
		return delegate.join(table, joiner, keySerde, valSerde);
	}

	@Override
	public <VO, VR> KStream<K, VR> outerJoin(KStream<K, VO> otherStream, ValueJoiner<? super V, ? super VO, ? extends VR> joiner, JoinWindows windows, Serde<K> keySerde, Serde<V> thisValueSerde, Serde<VO> otherValueSerde) {
		return delegate.outerJoin(otherStream, joiner, windows, keySerde, thisValueSerde, otherValueSerde);
	}

	@Override
	public <VO, VR> KStream<K, VR> outerJoin(KStream<K, VO> otherStream, ValueJoiner<? super V, ? super VO, ? extends VR> joiner, JoinWindows windows) {
		return delegate.outerJoin(otherStream, joiner, windows);
	}

	@Override
	public <VO, VR> KStream<K, VR> leftJoin(KStream<K, VO> otherStream, ValueJoiner<? super V, ? super VO, ? extends VR> joiner, JoinWindows windows, Serde<K> keySerde, Serde<V> thisValSerde, Serde<VO> otherValueSerde) {
		return delegate.leftJoin(otherStream, joiner, windows, keySerde, thisValSerde, otherValueSerde);
	}

	@Override
	public <VO, VR> KStream<K, VR> leftJoin(KStream<K, VO> otherStream, ValueJoiner<? super V, ? super VO, ? extends VR> joiner, JoinWindows windows) {
		return delegate.leftJoin(otherStream, joiner, windows);
	}

	@Override
	public <VT, VR> KStream<K, VR> leftJoin(KTable<K, VT> table, ValueJoiner<? super V, ? super VT, ? extends VR> joiner) {
		return delegate.leftJoin(table, joiner);
	}

	@Override
	public <VT, VR> KStream<K, VR> leftJoin(KTable<K, VT> table, ValueJoiner<? super V, ? super VT, ? extends VR> joiner, Serde<K> keySerde, Serde<V> valSerde) {
		return delegate.leftJoin(table, joiner, keySerde, valSerde);
	}

	@Override
	public <GK, GV, RV> KStream<K, RV> leftJoin(GlobalKTable<GK, GV> globalKTable, KeyValueMapper<? super K, ? super V, ? extends GK> keyValueMapper, ValueJoiner<? super V, ? super GV, ? extends RV> valueJoiner) {
		return delegate.leftJoin(globalKTable, keyValueMapper, valueJoiner);
	}

	@Override
	public <KR> KGroupedStream<KR, V> groupBy(KeyValueMapper<? super K, ? super V, KR> selector) {
		return delegate.groupBy(selector);
	}

	@Override
	public <KR> KGroupedStream<KR, V> groupBy(KeyValueMapper<? super K, ? super V, KR> selector, Serde<KR> keySerde, Serde<V> valSerde) {
		return delegate.groupBy(selector, keySerde, valSerde);
	}

	@Override
	public KGroupedStream<K, V> groupByKey() {
		return delegate.groupByKey();
	}

	@Override
	public KGroupedStream<K, V> groupByKey(Serde<K> keySerde, Serde<V> valSerde) {
		return delegate.groupByKey(keySerde, valSerde);
	}
}
