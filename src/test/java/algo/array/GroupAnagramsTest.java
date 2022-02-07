package algo.array;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;

public class GroupAnagramsTest {

    GroupAnagrams sut =new GroupAnagrams();
    @Test
    public void groupAnagrams() {
        final val lists = sut.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        assertTrue(lists.size()==3);
        assertThat(lists.get(1), hasItems("nat","tan"));
        assertThat(lists.get(2), hasItems("ate","eat","tea"));

    }

    @Test
    public void groupAnagrams2() {
        final val lists = sut.groupAnagrams(new String[]{"bdddddddddd","bbbbbbbbbbc"});
        assertEquals(2, lists.size());
//        assertThat(lists.get(1), hasItems("nat","tan"));
//        assertThat(lists.get(2), hasItems("ate","eat","tea"));

    }
}