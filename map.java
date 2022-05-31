public class App {
    public static void main(String[] args) throws Exception {
        int numVertex = 29;
        
        Graph map = new Graph(numVertex);

        map.addEdge('Brebes','Tegal',41);
        map.addEdge('Brebes','Slawi',29);
        map.addEdge('Tegal','Slawi',12);
        map.addEdge('Tegal','Pemalang',33);
        map.addEdge('Slawi','Purwokerto',91);
        map.addEdge('Purwokerto','Kroya',30);
        map.addEdge('Purwokerto','Cilacap',48);
        map.addEdge('Purwokerto','Purbalingga',35);
        map.addEdge('Purwokerto','Kebumen', 74)
        map.addEdge('Pemalang','Purbalingga',66);
        map.addEdge('Pemalang','Pekalongan',48);
        map.addEdge('Cilacap','Kroya',28);
        map.addEdge('Kroya','Kebumen',52);
        map.addEdge('Purbalingga','Banjarnegara',31);
        map.addEdge('Banjarnegara','Wonosobo',47);
        map.addEdge('Kebumen', 'Purworejo', 42);
        map.addEdge('Pekalongan','Kendal',75);
        map.addEdge('Purworerjo','Magelang',43);
        map.addEdge('Wonosobo','Magelang',60);
        map.addEdge('Wonosobo','Temanggung',40);
        map.addEdge('Magelang','Temanggung',21);
        map.addEdge('Magelang','Boyolali',65);
        map.addEdge('Kendal','Temanggung',70);
        map.addEdge('Kendal','Semarang',36);
        map.addEdge('Temanggung','Salatiga',55);
        map.addEdge('Salatiga','Semarang',50);
        map.addEdge('Salatiga','Boyolali',36);
        map.addEdge('Boyolali','Klaten',34);
        map.addEdge('Boyolali','Solo',29);
        map.addEdge('Semarang','Demak',34);
        map.addEdge('Demak','Kudus',38);
        map.addEdge('Demak','Purwodadi',34);
        map.addEdge('Solo','Purwodadi',65);
        map.addEdge('Solo','Sragen',35);
        map.addEdge('Solo','Sukoharjo',15);
        map.addEdge('Sukoharjo','Wonogiri',44);
        map.addEdge('Purwodadi','Kudus',55);
        map.addEdge('Purwodadi','Blora',67);
        map.addEdge('Kudus','Rembang',78);
        map.addEdge('Rembang','Blora',38);
        map.addEdge('Sragen','Blora',82);
    }
}
