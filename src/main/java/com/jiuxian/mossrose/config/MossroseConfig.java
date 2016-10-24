/**
 * Copyright 2015-2020 jiuxian.com.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jiuxian.mossrose.config;

import java.util.List;

import com.google.common.base.Preconditions;

/**
 * Mossrose meta configuration
 * 
 * @author <a href="mailto:wangyuxuan@jiuxian.com">Yuxuan Wang</a>
 *
 */
public class MossroseConfig {

	private Cluster cluster;
	private List<JobMeta> jobs;

	/**
	 * cluster meta
	 * 
	 * @author <a href="mailto:wangyuxuan@jiuxian.com">Yuxuan Wang</a>
	 *
	 */
	public static class Cluster {

		/**
		 * cluster name
		 */
		private String name;

		private LoadBalancingMode loadBalancingMode;

		public String getName() {
			return name;
		}

		public LoadBalancingMode getLoadBalancingMode() {
			return loadBalancingMode;
		}

		@Override
		public String toString() {
			return "Cluster [name=" + name + "]";
		}

		public enum LoadBalancingMode {
			ROUND_ROBIN, RANDOM;
		}

	}

	/**
	 * job meta
	 * 
	 * @author <a href="mailto:wangyuxuan@jiuxian.com">Yuxuan Wang</a>
	 *
	 */
	public static class JobMeta {
		/**
		 * job id
		 */
		private String id;
		/**
		 * cron
		 */
		private String cron;
		/**
		 * class name of job
		 */
		private String main;
		/**
		 * group
		 */
		private String group;
		/**
		 * If true, the job will run on any node in the cluster; If false，the
		 * job will run on master node
		 */
		private boolean runInCluster;
		/**
		 * description
		 */
		private String description;

		public String getId() {
			return id;
		}

		public String getCron() {
			return cron;
		}

		public String getMain() {
			return main;
		}

		public String getGroup() {
			return group;
		}

		public boolean isRunInCluster() {
			return runInCluster;
		}

		public String getDescription() {
			return description;
		}

		@Override
		public String toString() {
			return "JobMeta [id=" + id + ", cron=" + cron + ", main=" + main + ", group=" + group + "]";
		}

	}

	public Cluster getCluster() {
		return cluster;
	}

	public List<JobMeta> getJobs() {
		return jobs;
	}

	@Override
	public String toString() {
		return "MossroseConfig [cluster=" + cluster + ", jobs=" + jobs + "]";
	}

	public void validate() {
		Preconditions.checkNotNull(cluster);
		Preconditions.checkNotNull(cluster.getName());

		Preconditions.checkNotNull(jobs);
		Preconditions.checkArgument(jobs.size() > 0);
		for (JobMeta meta : jobs) {
			Preconditions.checkNotNull(meta.getCron());
			Preconditions.checkNotNull(meta.getMain());
		}
	}

}
