package application;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainController {
	
	@FXML
	private Label result;
	private long number1=0;
	private String operator="";
	private boolean start=true;
	private ModelClass model=new ModelClass();
	@FXML
	public void processNumber(ActionEvent event)
	{
		if(start)
		{
			result.setText("");
			start=false;
		}
		String value=((Button)event.getSource()).getText();
		result.setText(result.getText()+value);
	}
	
	
	@FXML
	public void processOperator(ActionEvent event)
	{
		String value=((Button)event.getSource()).getText();
		if(!value.equals("="))
		{
			if(!operator.isEmpty())
				return;
			
			operator=value;
			number1=Long.parseLong(result.getText());
			result.setText("");
		}
		else
		{
			if(operator.isEmpty())
				return;
			long number2=Long.parseLong(result.getText());
			float output=model.Calculate(number1, number2, operator);
			result.setText(String.valueOf(output));
			operator="";
			start=true;
		}
	}

}

/*Method <processNumbers> 
This method is set On Action in each of the number buttons. 
Boolean "start" is created to create empty string only once,
because of that, you can type multiple-digit numbers. 
Variable Label "result" is just an identity of calculator's Label, 
you can think of that as a kind of conduit between calculator's label and 
controller class, also, because of that, you have to always use "setText" and 
"getText" methods. Because Label's text is of a type String, "+" sign doesn't add up values.

Method<processOperators> 
This method is set On Action in each of the operator buttons. The first is 
very clear - String "value" is now equal to the chosen operator. If it's not " = " 
it moves forward to the another if statement, it says "if operator isn't empty, stop 
the method here" but in fact, operator IS empty, so the code goes on. It prevents from 
typing more that one operator. 
After this, operator is set equal to value, i.e. (chosen operator button).
Now, number1 is converted to variable of type Long, and result is again empty,
 to make some place for a new number (result is actually a bit confusing name for this).
From this point, this method is over, and user have to type another digit, to activate first method one more time. 
Since you have both numbers, you choose equal sign button. First if statement means, 
that you can't have a result if you didn't choose any operator first. 
(to prevent from using equal sing in a beginning of an operation). 
The rest of the code is very easy to understand. "valueOf" makes String from float, to set it as the label text.*/
