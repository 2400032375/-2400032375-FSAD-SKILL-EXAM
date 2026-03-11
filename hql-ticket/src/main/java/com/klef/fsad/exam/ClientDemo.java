package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ClientDemo 
{
    public static void main(String[] args) 
    {
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();

        // Insert Record
        Ticket t = new Ticket(1,"Bus Ticket","12-03-2026","Booked");
        session.save(t);

        tx.commit();

        // Update using HQL positional parameters

        session.beginTransaction();

        Query query = session.createQuery(
        "update Ticket set name=?1 , status=?2 where id=?3");

        query.setParameter(1,"Train Ticket");
        query.setParameter(2,"Confirmed");
        query.setParameter(3,1);

        query.executeUpdate();

        session.getTransaction().commit();

        session.close();
        sf.close();

        System.out.println("Record Inserted and Updated Successfully");
    }
}