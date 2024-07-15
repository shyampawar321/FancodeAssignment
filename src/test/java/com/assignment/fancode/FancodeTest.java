package com.assignment.fancode;

import com.assignment.constants.StatusCodeEnum;
import com.assignment.core.TestBase;
import com.assignment.endpoints.Endpoints;
import com.assignment.model.Todo;
import com.assignment.model.User;
import com.assignment.utils.ResponseArrayUtils;
import com.assignment.utils.TestHelper;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FancodeTest extends TestBase {
	ExtentReports extent = new ExtentReports();
	ExtentSparkReporter spark = new ExtentSparkReporter("Reports/TestResults.html");
	public ExtentTest logger;

	@BeforeTest
	public void beforeTest() {
		extent.attachReporter(spark);
	}

	@Test
	public void TaskCompletedMoreThan50PercentTest() {
		double passPercentValue = 50;
		String queryParamAttribute = "userId";
		
		ExtentTest test = extent.createTest("Getting the users who have completed Task more than 50%");
		logger = test;
		Response userResponse = given().when().get(Endpoints.USERS);
		Assert.assertTrue(userResponse.getStatusCode() == StatusCodeEnum.Code_200.getCode(), "Response Failed");
		test.pass("Response recieved successfully");

		test.log(Status.INFO, "User Fetched from the endpoint");
		User[] usersArray = ResponseArrayUtils.getResponseAsArray(userResponse, User[].class);

		for (User user : usersArray) {
			double userLat = Double.parseDouble(user.getAddress().getGeo().getLat());
			double userLng = Double.parseDouble(user.getAddress().getGeo().getLng());

			test.log(Status.INFO, "Checking for the condition of Lattitude and Longitude for user : " + user.getName());
			if (TestHelper.LatituteLongitudeCheckerForUser(userLat, userLng)) {
				String userName = user.getName();
				test.log(Status.INFO, "Fetching the Todo list of " + userName
						+ " who matched the condition of Lattitude and Longitude ");
				Response todoResponse = given().queryParam(queryParamAttribute, user.getId()).when().get(Endpoints.TODOS);
				Assert.assertTrue(todoResponse.getStatusCode() == StatusCodeEnum.Code_200.getCode(), "Response Failed");

				Todo[] todoArray = ResponseArrayUtils.getResponseAsArray(todoResponse, Todo[].class);

				int totalTask = todoArray.length;
				int completedTask = 0;

				test.log(Status.INFO, "Getting the count for completed task for the user : " + userName);
				for (Todo todo : todoArray) {
					if (todo.isCompleted() == true) {
						completedTask++;
					}
				}

				test.log(Status.INFO, "Checking the Task Completed Percentage more than 50% for user : " + userName);
				Assert.assertTrue(TestHelper.CalculateTaskCompletedPercentage(completedTask, totalTask) > passPercentValue,
						"User " + user.getId() + "In the Fancode city has less than 50% Task Completed");
			}
		}
	}

	
	  @AfterMethod 
	  public void getResult(ITestResult result) throws Exception
	  {
		  switch(result.getStatus()) 
		  {
		  case ITestResult.FAILURE:
			  logger.log(Status.FAIL,MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			  break;
		  case ITestResult.SKIP:
			  logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
			  break;
		  case ITestResult.SUCCESS:
			  logger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case Passed", ExtentColor.GREEN));
			  break;
		  default:
			  logger.log(Status.INFO, MarkupHelper.createLabel(result.getName()+" Test Case status not present", ExtentColor.BLUE));
		  }

	 }
	 
	@AfterTest
	public void afterTest() {
		extent.flush();
	}

}
