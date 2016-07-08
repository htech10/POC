package com.hpandey.core.mongodb;

import java.net.UnknownHostException;
import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

/**
 * Sample Program to connect MongoDB using Java
 *
 */
public class App 
{
	//Query key-value from tbl collection
	public static DBCursor searchUNDGNumber(String key, String value, DBCollection tbl) {
		BasicDBObject query=new BasicDBObject();
		query.put(key, value);
		
		DBCursor cursor=tbl.find(query);
		
		return cursor;
	}
	
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        DBCollection tbl=null;
        try {

			MongoClient mdb=new MongoClient("localhost",27017);
			
			DB db=mdb.getDB("codes");   //point to or create the database
			tbl=db.getCollection("DG"); //create collection
			
			BasicDBObject doc=new BasicDBObject(); //create document to story key-value pair
			doc.put("UNDGNumber","78654");
			doc.put("Class", "A");
			doc.put("TechName","HP & SS Tech");
			doc.put("AdditionalInfo", 100);
			doc.put("DateCreated",new Date());
			tbl.insert(doc);
		
		} catch (UnknownHostException e) {

			e.printStackTrace();
		} catch (MongoException e) {
		
			e.printStackTrace();
		}
    
        DBCursor search=searchUNDGNumber("UNDGNumber","78654",tbl);
       
        while (search.hasNext()) {
    		System.out.println(search.next());
    	}
       
    }
}
