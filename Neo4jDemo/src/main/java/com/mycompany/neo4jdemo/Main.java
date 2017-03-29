package com.mycompany.neo4jdemo;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class Main {
    
    // uma função que implementa um interface do proprio neo4j para definir os tipos
    public enum NodeType implements Label{
        Person, Course;
    }
    // funçao que implementa uma interface do proprio neo4j para definir os tipos
    
    public enum RelationType implements RelationshipType{
        Knows, BelongsTo;
    }
    
    public static void main(String[] args) {
        
        
        GraphDatabaseFactory dbFactory = new GraphDatabaseFactory();
        GraphDatabaseService graphDB = dbFactory.newEmbeddedDatabase("/var/lib/neo4j/data/");   

        try(Transaction tx = graphDB.beginTx()){
            
            // criando um nodo pessoa
            Node bobNode = graphDB.createNode(NodeType.Person);
            bobNode.setProperty("PId", 5001);
            bobNode.setProperty("Name","Bob");
            bobNode.setProperty("Age",23);
        
            // criando mais um nodo pessoa
            Node aliceNode = graphDB.createNode(NodeType.Person);
            aliceNode.setProperty("PId", 5002);
            aliceNode.setProperty("Name","Bob");
            
            // mais uma pessoa 
            Node eveNode = graphDB.createNode(NodeType.Person);
            eveNode.setProperty("Name","gabiru");
            
            // criando um nodo do tipo curso
            Node itNode = graphDB.createNode(NodeType.Course);
            itNode.setProperty("id", 1);
            itNode.setProperty("Name", "IT begginers");
            itNode.setProperty("Location", "room 153");
            
            // mais um 
            
            Node eletronicNode = graphDB.createNode(NodeType.Course);
            eletronicNode.setProperty("Name", "Eletronics advanced");
            
            
            // criando relaçoes entre os nodos
            
            bobNode.createRelationshipTo(aliceNode, RelationType.Knows);
            
            Relationship bobrelIt = bobNode.createRelationshipTo(itNode,RelationType.BelongsTo);
            bobrelIt.setProperty("Function", "student");
            
            
            Relationship bobRelEletronics = bobNode.createRelationshipTo(eletronicNode, RelationType.BelongsTo);
            bobRelEletronics.setProperty("Function", "Tuply Teacher");
            
            Relationship aliceRelIt = aliceNode.createRelationshipTo(itNode, RelationType.BelongsTo);
            aliceRelIt.setProperty("Function", "Teacher");
            
            tx.success(); 
        }
        graphDB.shutdown();
    }
}
