package com.prs.business;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.prs.db.DBUtil;

public class PurchaseRequestDB {
	public static PurchaseRequest getPurchaseRequestById(int purchaseRequestID) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			PurchaseRequest purchaseRequest = em.find(PurchaseRequest.class, purchaseRequestID);
			
			return purchaseRequest;
		}
		finally {
			em.close();
			//DBUtil.closeEMF();
		}
		
	}

	public static List<PurchaseRequest> getAll() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		List<PurchaseRequest> purchaseRequest = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT r FROM PurchaseRequest r");
			purchaseRequest = q.getResultList();
			
		}
		finally {
			em.close();
			//DBUtil.closeEMF();
		}
		return purchaseRequest;
	}

	public static void insert(PurchaseRequest purchaseRequest) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(purchaseRequest);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();

		}
	}
	public static boolean delete(PurchaseRequest purchaseRequest) {
		boolean success = false;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			PurchaseRequest pr = em.find(PurchaseRequest.class, purchaseRequest.getId());
		em.merge(pr);
		em.remove(pr);
		trans.commit();
		success=true;
	} catch (Exception e) {
		System.out.println(e);
		trans.rollback();
	} finally {
		em.close();
	}
		return success;
}
	
		
}