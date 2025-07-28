package com.example.loginapp.controller;

import com.example.loginapp.dto.ProfileRequest;
import com.example.loginapp.model.BankDetails;
import com.example.loginapp.model.Business;
import com.example.loginapp.model.ProductPickup;
import com.example.loginapp.model.User;
import com.example.loginapp.repository.BankDetailsRepository;
import com.example.loginapp.repository.BusinessRepository;
import com.example.loginapp.repository.ProductPickupRepository;
import com.example.loginapp.repository.UserRepository;
//import com.example.loginapp.service.EmailService;
import com.example.loginapp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;



import java.io.File;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:3000")  // If frontend runs on 3000
public class UserController {

    @Autowired
    private UserRepository userRepository;;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {

        user.setOtp(null);         // clear OTP
        user.setOtpVerified(true);

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }



    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginData) {
        String mobile = loginData.get("mobile");
        String password = loginData.get("password");

        Optional<User> user = userRepository.findByMobileAndPassword(mobile, password);

        if (user.isPresent()) {
//            return ResponseEntity.ok("Login successful!");
                return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }


    @Autowired
    private BusinessRepository businessRepository;

    // Get user by ID (for auto-filling Profile1)
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }


    // Save Profile1 data
    @PostMapping("/profile1")
    public ResponseEntity<String> saveProfile(@RequestBody ProfileRequest profileRequest) {

        System.out.println("Received altMobile: " + profileRequest.getAltMobile());

        Optional<User> optionalUser = userRepository.findById(profileRequest.getUserId());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            user.setAltMobile(profileRequest.getAltMobile());

            user.setEmail(profileRequest.getEmail());
            user.setAltMobile(profileRequest.getAltMobile());
            userRepository.save(user);

            Business business = businessRepository.findByUserId(user.getId());


            if (business == null) {
                business = new Business();
                business.setUser(user);

                // 🔽 Generate and set custom business ID
                String businessId = generateNextBusinessId();
                business.setBusinessId(businessId);

                
            }

            business.setCities(profileRequest.getCities());
            businessRepository.save(business);

            return ResponseEntity.ok("Profile saved successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    // 🔽 Add this private method below the endpoints
    private String generateNextBusinessId() {
        long count = businessRepository.count();
        return "b" + (101 + count);  // Generates: b101, b102, ...
    }

    private static final String UPLOAD_DIR = "uploads/";


    //    @PostMapping("/api/business")
@PostMapping("/profile2")
public ResponseEntity<String> updateBusinessProfile(
        @RequestParam("userId") Long userId,
        @RequestParam("businessName") String businessName,
        @RequestParam("businessType") String businessType,
        @RequestParam("address") String address,
        @RequestParam("city") String city,
        @RequestParam("district") String district,
        @RequestParam("pincode") String pincode,
        @RequestParam("state") String state,
        @RequestParam("gstNumber") String gstNumber,
        @RequestParam("whySell") String whySell,

        @RequestParam("panOrAadhar") MultipartFile panOrAadhar,
        @RequestParam("license") MultipartFile license,
        @RequestParam("gstCertificate") MultipartFile gstCertificate,
        @RequestParam("companyLogo") MultipartFile companyLogo
) {
    try {
        Optional<User> userOpt = userRepository.findById(userId);
        if (!userOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        User user = userOpt.get();
        Business business = businessRepository.findByUserId(user.getId());
        if (business == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Business not found. Please complete Profile1 first.");
        }

        // Create upload folder if missing
        String uploadDir = "uploads/";
        File folder = new File(uploadDir);
        if (!folder.exists()) folder.mkdirs();

        // Save files
//        String panPath = uploadDir + System.currentTimeMillis() + "_pan_" + panOrAadhar.getOriginalFilename();
//        String licensePath = uploadDir + System.currentTimeMillis() + "_license_" + license.getOriginalFilename();
//        String gstPath = uploadDir + System.currentTimeMillis() + "_gst_" + gstCertificate.getOriginalFilename();
//        String logoPath = uploadDir + System.currentTimeMillis() + "_logo_" + companyLogo.getOriginalFilename();


        // ✅ Clean file names to remove spaces
        String safePanName = panOrAadhar.getOriginalFilename().replaceAll("\\s+", "_");
        String safeLicenseName = license.getOriginalFilename().replaceAll("\\s+", "_");
        String safeGstName = gstCertificate.getOriginalFilename().replaceAll("\\s+", "_");
        String safeLogoName = companyLogo.getOriginalFilename().replaceAll("\\s+", "_");

        // ✅ Build file paths using cleaned names
        String panPath = UPLOAD_DIR + System.currentTimeMillis() + "_pan_" + safePanName;
        String licensePath = UPLOAD_DIR + System.currentTimeMillis() + "_license_" + safeLicenseName;
        String gstPath = UPLOAD_DIR + System.currentTimeMillis() + "_gst_" + safeGstName;
        String logoPath = UPLOAD_DIR + System.currentTimeMillis() + "_logo_" + safeLogoName;

        panOrAadhar.transferTo(new File(panPath));
        license.transferTo(new File(licensePath));
        gstCertificate.transferTo(new File(gstPath));
        companyLogo.transferTo(new File(logoPath));

        // Update existing business
        business.setBusinessName(businessName);
        business.setBusinessType(businessType);
        business.setAddress(address);
        business.setCity(city);
        business.setDistrict(district);
        business.setPincode(pincode);
        business.setState(state);
        business.setGstNumber(gstNumber);
        business.setWhySell(whySell);

        business.setPanOrAadharPath(panPath);
        business.setLicensePath(licensePath);
        business.setGstCertificatePath(gstPath);
        business.setCompanyLogoPath(logoPath);

        businessRepository.save(business);

        return ResponseEntity.ok("✅ Business profile updated successfully (Profile2)");
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("❌ Failed to update profile2: " + e.getMessage());
    }
}


    @PostMapping("/profile2-simple")
    public ResponseEntity<String> saveBusinessSimple(@RequestBody Map<String, String> data) {
        try {
            Long userId = Long.parseLong(data.get("userId"));
            Optional<User> userOpt = userRepository.findById(userId);
            if (!userOpt.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            Business business = businessRepository.findByUserId(userId);
            if (business == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please complete Profile1 first");
            }

            business.setBusinessName(data.get("businessName"));
            business.setBusinessType(data.get("businessType"));
            business.setAddress(data.get("address"));
            business.setCity(data.get("city"));
            business.setDistrict(data.get("district"));
            business.setPincode(data.get("pincode"));
            business.setState(data.get("state"));
            business.setGstNumber(data.get("gstNumber"));
            business.setWhySell(data.get("whySell"));

            businessRepository.save(business);

            return ResponseEntity.ok("Profile2 submitted without file uploads.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @Autowired
    private BankDetailsRepository bankDetailsRepository;

    @Autowired
    private ProductPickupRepository productPickupRepository;

    @PostMapping("/profile3")
    public ResponseEntity<String> saveBankAndPickupDetails(@RequestBody Map<String, String> data) {
        Long userId = Long.parseLong(data.get("userId"));
        Optional<User> userOpt = userRepository.findById(userId);
        if (!userOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        User user = userOpt.get();

        // Generate custom bankId
        long bankCount = bankDetailsRepository.count();
        String bankId = "bnk_" + (1101 + bankCount);

        BankDetails bankDetails = new BankDetails();
        bankDetails.setUser(user);
        bankDetails.setBankId(bankId);
        bankDetails.setAccountNumber(data.get("accountNumber"));
        bankDetails.setIfscCode(data.get("ifscCode"));
        bankDetails.setBankName(data.get("bankName"));
        bankDetails.setBranch(data.get("branch"));
        bankDetails.setCity(data.get("bankCity"));
        bankDetails.setState(data.get("bankState"));
        bankDetailsRepository.save(bankDetails);

        // Product pickup
        long productCount = productPickupRepository.count();
        String productId = "Product_p_" + (1011 + productCount);

        ProductPickup pickup = new ProductPickup();
        pickup.setUser(user);
        pickup.setProductId(productId);
        pickup.setPickupAddress(data.get("pickupAddress"));
        pickup.setCity(data.get("pickupCity"));
        pickup.setState(data.get("pickupState"));
        pickup.setPincode(data.get("pickupPincode"));
        productPickupRepository.save(pickup);

        return ResponseEntity.ok("✅ Bank and product pickup details saved successfully");
    }


    @PostMapping("/profile4")
    public ResponseEntity<String> saveProfile4Details(@RequestBody Map<String, String> data) {
        try {
            Long userId = Long.parseLong(data.get("userId"));
            Optional<User> userOpt = userRepository.findById(userId);

            if (userOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            Business business = businessRepository.findByUserId(userId);
            if (business == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Business profile not found. Complete Profile1 & Profile2 first.");
            }

            business.setNatureOfCompany(data.get("natureOfCompany"));
//            business.setRegistrationDate(LocalDate.parse(data.get("registrationDate")));

            String dateStr = data.get("registrationDate");
            if (dateStr != null && !dateStr.trim().isEmpty()) {
                business.setRegistrationDate(LocalDate.parse(dateStr));
            }

            business.setNumberOfEmployees(data.get("numberOfEmployees"));

            business.setEcommerceDepartment(Boolean.parseBoolean(data.get("ecommerceDepartment")));
            business.setSellsOnEcommerce(Boolean.parseBoolean(data.get("sellsOnEcommerce")));
            business.setHasDealerNetwork(Boolean.parseBoolean(data.get("hasDealerNetwork")));

            business.setTurnoverYear1(data.get("turnoverYear1"));
            business.setTurnoverYear2(data.get("turnoverYear2"));
            business.setTurnoverYear3(data.get("turnoverYear3"));

            businessRepository.save(business);

            return ResponseEntity.ok("✅ Profile4 data saved successfully");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("❌ Failed to save Profile4 data: " + e.getMessage());
        }
    }





    @Autowired
    private EmailService emailService;
    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOtp(@RequestBody Map<String, String> request) {
        String phone = request.get("mobile");
        String email = request.get("email");

        Optional<User> existing = userRepository.findByMobile(phone);
        if (existing.isPresent()) {
            return ResponseEntity.badRequest().body("User already registered with this phone.");
        }

        String otp = String.valueOf((int)(Math.random() * 900000) + 100000);
        System.out.println("Generated OTP: " + otp + " for phone: " + phone + ", email: " + email);

        // ✅ Just store OTP temporarily in memory (if needed, you can cache or use Redis)
        // 👉 For now, return to frontend only for testing
        Map<String, String> response = new HashMap<>();
        response.put("otp", otp); // Remove this in production

        // ✅ Send via email

        emailService.sendOtpEmail(email, otp);

        return ResponseEntity.ok(response);
    }

    



    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody Map<String, String> request) {
        String phone = request.get("mobile");
        String enteredOtp = request.get("otp");

        Optional<User> optionalUser = userRepository.findByMobile(phone);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        User user = optionalUser.get();
        if (user.getOtp() != null && user.getOtp().equals(enteredOtp)) {
            user.setOtpVerified(true);
            user.setOtp(null); // Clear OTP after verification
            userRepository.save(user);
            return ResponseEntity.ok("OTP verified successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP.");
        }
    }


}

