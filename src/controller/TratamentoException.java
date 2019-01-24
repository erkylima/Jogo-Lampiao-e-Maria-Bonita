	package controller;

public class TratamentoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void printStackTrace() {
		System.out.println("Aconteceu algum erro");
	}
}
