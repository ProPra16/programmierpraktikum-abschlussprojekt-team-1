package tracking;

import java.util.List;

import vk.core.api.TestFailure;

public class ErrorEvent extends Event {
	List<TestFailure> errors;
	public ErrorEvent(List<TestFailure> errors){
		this.errors = errors;
	}
	public List<TestFailure> getErrors(){
		return errors;
	}
}
