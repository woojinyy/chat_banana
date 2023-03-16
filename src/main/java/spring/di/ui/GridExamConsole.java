package spring.di.ui;

import spring.di.entity.Exam;

public class GridExamConsole implements ExamConsole {
	private Exam exam;
	
	public GridExamConsole(Exam exam) {
		
			this.exam = exam;
		}
	@Override
	public void print() {
		// TODO Auto-generated method stub

	}

}
