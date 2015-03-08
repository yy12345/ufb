package com.ufufund.test.test3;

import com.ufufund.ufb.common.cache.Cache;
import com.ufufund.ufb.common.cache.CacheManager;







public class TestCache {
  public static void main(String[] args) {
	  Cache s = new Cache();
	  CacheManager.putCacheInfo("aaa", s, 1, true);
      //for(){
	  System.out.println(CacheManager.getCacheInfo("aaa"));
      

  }
}
