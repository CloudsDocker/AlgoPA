package algo;

import java.util.*;

public class FindItenary {

    public static void main(String[] args) {
//        [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
//        List<List<String>> list = new ArrayList<List<String>>();
//        list.add(new ArrayList<String>(){{add("MUC");add("LHR");}});
//        list.add(new ArrayList<String>(){{add("JFK");add("MUC");}});
//        list.add(new ArrayList<String>(){{add("SFO");add("SJC");}});
//        list.add(new ArrayList<String>(){{add("LHR");add("SFO");}});

        // failing test case
//        [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
        List<List<String>> list = new ArrayList<List<String>>();
        list.add(new ArrayList<String>(){{add("JFK");add("SFO");}});
        list.add(new ArrayList<String>(){{add("JFK");add("ATL");}});
        list.add(new ArrayList<String>(){{add("SFO");add("ATL");}});
        list.add(new ArrayList<String>(){{add("ATL");add("JFK");}});
        list.add(new ArrayList<String>(){{add("ATL");add("SFO");}});

//        System.out.println("===:"+new FindItenary().findItinerary_todd(list));
        System.out.println("===:"+new FindItenary().findItinerary(list));
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets)
            targets.computeIfAbsent(ticket.get(0), k -> new PriorityQueue()).add(ticket.get(1));
        visit("JFK");
        return route;
    }

    Map<String, PriorityQueue<String>> targets = new HashMap<>();
    List<String> route = new LinkedList();

    void visit(String airport) {
        while(targets.containsKey(airport) && !targets.get(airport).isEmpty())
            visit(targets.get(airport).poll());
        route.add(0, airport);
    }

        public List<String> findItinerary_todd(List<List<String>> tickets) {
            System.out.println(tickets.toString());
            if(tickets==null) return null;
            Map<String, TreeSet<String>> map = new HashMap();
            tickets.stream().forEach(it-> {
                        //String[] ary = it.split(",",2);
                       // System.out.println("====:"+it.get(0)+","+it.get(1));
                        TreeSet set = map.getOrDefault(it.get(0), new TreeSet<String>());
                        set.add(it.get(1));
                        map.put(it.get(0),set);//[!!!!] That common error for Map, should getOrDefault, add value and Lastly Put it back to Map
                    }
            );
            List<String> listRtn=new ArrayList();
            String next="JFK";
            listRtn.add(next);

            while(map.get(next)!=null){
                //System.out.println("--:"+next);
                String tmp=map.get(next).first();
                //System.out.println("-22-:"+tmp);
                listRtn.add(tmp);
                next=tmp;
            }
            return listRtn;
        }
    }
