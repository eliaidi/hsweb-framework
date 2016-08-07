package org.hsweb.generator.app.register;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 抽象注册器实现
 *
 * @param <T>
 */
public abstract class AbstractRegister<T> implements Register<T> {

    protected List<Wrapper<T>> wrapperList = new ArrayList<>();

    /**
     * 注册一个包装器，可多次注册。多次注册包装器不会相互覆盖。
     */
    @Override
    public void register(Wrapper<T> data) {
        wrapperList.add(data);
    }

    /**
     * 获取合并的数据
     */
    public abstract T getMergedData();

    /**
     * 获取包装器里的所有数据
     */
    public List<T> getDataList() {
        return wrapperList.stream().map(Wrapper::get).collect(Collectors.toList());
    }

}
