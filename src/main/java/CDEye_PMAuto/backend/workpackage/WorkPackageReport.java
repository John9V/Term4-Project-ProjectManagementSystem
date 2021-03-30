package CDEye_PMAuto.backend.workpackage;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("workPackageReport")
@RequestScoped
public class WorkPackageReport implements Serializable {

	@Inject WorkPackageManager wp;
	
	public void spoolReport() {
		WorkPackage[] wpl = wp.getAll();
		
		for (WorkPackage wp : wpl) {
			System.out.println("======================================================");
			System.out.println("WORK PACKAGE: " + wp.getWorkPackageNumber() + " in Project: " + wp.getProject().getProjectName());
			System.out.println("budget: " + wp.getProjectBudget());
			System.out.println("allocated: " + wp.calcAllocatedBudget());
			System.out.println("unallocated: " + wp.calcUnallocatedBudget());
			System.out.println("estimated budget: " + wp.calcRespEngBudget());
			System.out.println("estimated pds: " + wp.calcRespEngPersonDays());
			System.out.println("completed budget: " + wp.getCompletedBudget());
			System.out.println("completed person days: " + wp.getCompletedPersonDays());
			
//			if (wp.calcRespEngBudget() == null) {
//				wp.setCalcRespEngBudget(new BigDecimal(0));
//			}
			if (wp.getCompletedBudget() == null) {
				wp.setCompletedBudget(new BigDecimal(0));
			}
			if (wp.getCompletedPersonDays() == null) {
				wp.setCompletedPersonDays(new BigDecimal(0));
			}
//			if (wp.getCompletedPersonDays() == null) {
//				
//			}
			System.out.println("remaining budget: " + (wp.calcRespEngBudget().subtract(wp.getCompletedBudget())));
			System.out.println("remaining person days: " + (wp.calcRespEngPersonDays().subtract(wp.getCompletedPersonDays())));
			System.out.println("======================================================");
		}
	}
}
