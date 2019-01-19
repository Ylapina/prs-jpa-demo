package com.prs.business;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.prs.db.DBUtil;

public class PurchaseRequestLineItemDB {
	public static PurchaseRequestLineItem getPurchaseRequestLineItemById(int purchaseRequestLineItemID) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			PurchaseRequestLineItem purchaseRequestLineItem = em.find(PurchaseRequestLineItem.class, purchaseRequestLineItemID);
			
			return purchaseRequestLineItem;
		}
		finally {
			em.close();
			//DBUtil.closeEMF();
		}
		
	}

	public static List<PurchaseRequestLineItem> getAll() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		List<PurchaseRequestLineItem> purchaseRequestLineItem = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT pr FROM PurchaseRequestLineItem pr");
			purchaseRequestLineItem = q.getResultList();
			
		}
		finally {
			em.close();
			//DBUtil.closeEMF();
		}
		return purchaseRequestLineItem;
	}

	public static void insert(PurchaseRequestLineItem purchaseRequestLineItem) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(purchaseRequestLineItem);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();

		}
	}
	public static void update(PurchaseRequestLineItem purchaseRequestLineItem) {
		EntityManager em= DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans= em.getTransaction();
		trans.begin();
		try {
			em.merge(purchaseRequestLineItem);
			trans.commit();
		}catch (Exception e) {
			System.out.println(e);
			trans.rollback();;
		}finally {
			em.close();
		}
	}
	public static boolean delete(PurchaseRequestLineItem purchaseRequestLineItem) {
		boolean success = false;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			PurchaseRequestLineItem pl = em.find(PurchaseRequestLineItem.class, purchaseRequestLineItem.getId());
		em.merge(pl);
		em.remove(pl);
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