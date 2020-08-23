
import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

public class ObjectSize {

    private static final int countSize = 1000;

    private static final Logger log = LoggerFactory.getLogger(ObjectSize.class);

    public static void calculateSize() {
        log.info(VM.current().details());

        int[] intArray = new int[countSize];
        char[] charString = new char[]{'a'};
        Integer[] integerArray = new Integer[countSize];
        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();
        HashSet<Integer> hashSet = new HashSet<>();
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        ConcurrentHashMap<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>();

        for (int i = 0; i < countSize; i++) {
            Integer io = i;
            intArray[i] = io;
            integerArray[i] = io;
            arrayList.add(io);
            linkedList.add(io);
            hashSet.add(io);
            hashMap.put(i, i);
            concurrentHashMap.put(i, i);
        }

        arrayList.trimToSize();

        log.info("====================================================================");
        log.info(">> new Object()");
        log.info(GraphLayout.parseInstance(new Object()).toPrintable());
        log.info("====================================================================");
        log.info(">> new Integer(" + countSize + ")");
        log.info(GraphLayout.parseInstance(new Integer(countSize)).toPrintable());
        log.info("====================================================================");
        log.info(">> new Long(" + countSize + ")");
        log.info(GraphLayout.parseInstance(new Long(countSize)).toPrintable());
        log.info("====================================================================");
        log.info(">> new int[" + countSize + "]");
        log.info(GraphLayout.parseInstance((Object) intArray).toFootprint());
        log.info("====================================================================");
        log.info(">> new Integer[" + countSize + "]");
        log.info(GraphLayout.parseInstance((Object) integerArray).toFootprint());
        log.info("====================================================================");
        log.info(">> new ArrayList<Integer>(" + countSize + ")");
        log.info(GraphLayout.parseInstance(arrayList).toFootprint());
        log.info("====================================================================");
        log.info(">> new LinkedList<Integer>(" + countSize + ")");
        log.info(GraphLayout.parseInstance(linkedList).toFootprint());
        log.info("====================================================================");
        log.info(">> new HashSet<Integer>(" + countSize + ")");
        log.info(GraphLayout.parseInstance(hashSet).toFootprint());
        log.info("====================================================================");
        log.info(">> new HashMap<Integer>(" + countSize + ")");
        log.info(GraphLayout.parseInstance(hashMap).toFootprint());
        log.info("====================================================================");
        log.info(">> new ConcurrentHashMap<Integer>(" + countSize + ")");
        log.info(GraphLayout.parseInstance(concurrentHashMap).toFootprint());
        log.info("====================================================================");
        log.info(">> new ArrayList<Integer>(" + countSize + ")" + ".stream()");
        log.info(GraphLayout.parseInstance(arrayList.stream()).toFootprint());
        log.info("====================================================================");

        log.info("====================================================================");
        log.info(">> new char");
        log.info(GraphLayout.parseInstance(charString).toPrintable());

        log.info("====================================================================");
        log.info(">> new String()");
        log.info(GraphLayout.parseInstance(new String()).toPrintable());
        log.info("====================================================================");
        log.info(">> new String(a)");
        log.info(GraphLayout.parseInstance("a").toPrintable());
        log.info("====================================================================");
        log.info(">> new String(aaaa) 4 items");
        log.info(GraphLayout.parseInstance("aaaa").toPrintable());
        log.info("====================================================================");
        log.info(">> new String(a) 5 items");
        log.info(GraphLayout.parseInstance("aaaaa").toPrintable());
        log.info("====================================================================");

        log.info(">> Custom objects:");
        log.info("====================================================================");
        log.info(">> new UserAccount");
        log.info(GraphLayout.parseInstance(new UserAccount(1, 100, 30)).toPrintable());
        log.info("====================================================================");
    }
}
