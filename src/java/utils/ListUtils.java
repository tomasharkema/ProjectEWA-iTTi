package utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by tomas on 10-01-15.
 */
public class ListUtils {
    static public <B, A extends B> List<B> castList(List<A> list) {
        return (List<B>)CollectionUtils.collect(list, new Transformer<A, B>() {
            @Override
            public B transform(A a) {
                return a;
            }
        });
    }
}

