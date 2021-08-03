package Assessment_MoneyLion.Assessment;


import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController 
@RequestMapping(path="/") 
public class MainController {
  @Autowired 
  private UserRepository userRepository;
  private User n;
  
  //To add dummy data to test get and post method
  @PostMapping("/add") 
  public @ResponseBody String addNewUser (@RequestParam String name
      , @RequestParam String email
      , @RequestParam Boolean access) {
    
    n = new User();
    n.setName(name);
    n.setEmail(email);
    n.setAccess(access);
    
    userRepository.save(n);
    
    return "Saved";
  }
  
  //To view all the data saved
  @GetMapping("/all")
  public @ResponseBody Iterable<User> getAllUsers() {
    // This returns a JSON 
    return userRepository.findAll();
  }
  
  //Get method 
  @GetMapping("/feature")
	public HashMap<String,Boolean> greeting(@RequestParam Map<String,String> requestParams ) {
	  
	   String email=requestParams.get("email");
	   String featureName=requestParams.get("featureName");	
	   
	   HashMap<String, Boolean> map = new HashMap<String, Boolean>();
	   
	   String result = userRepository.findByEmail(email);
	   String name = userRepository.findByname(result);
	   Boolean access = userRepository.findByaccess(result);
	   
	   
	  if(result.equals(email) && name.equals(featureName)) {
		   map.put("canAccess", access);
		   return map; 
	  }
	   
	  map.put("Data does not exist in database", false);
	  return map;
	   
	}
  
  
  //Post method
  @PostMapping("/feature")
  @ResponseBody
  public  ResponseEntity<?>  test(@RequestBody User user) {
	  
	 if(user.getAccess() == true) {return new ResponseEntity<>(HttpStatus.OK);}
	 
	 return  new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	  
  }
  
}
