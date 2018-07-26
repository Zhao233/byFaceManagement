package com.duckduckgogogo.controller;

import com.duckduckgogogo.domain.User;
import com.duckduckgogogo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/console")
public class ConsoleController {

    @Autowired
    private UserService userService;

    @RequestMapping
    public ModelAndView toIndex() {
        return new ModelAndView("console/employee_management");
    }

    @RequestMapping("/profile")
    public ModelAndView toProfile(HttpServletRequest request) throws Exception {
        Map<String, Object> model = new HashMap<>();

        User current = (User) request.getSession().getAttribute("user");
        if (current != null) {
            User user = userService.findById(current.getId());
            if (user != null) user.setPassword("");

            model.put("user", user);
        }

        return new ModelAndView("console/profile", model);
    }

    /**
     * PROJECT MANAGEMENT
     */
    @RequestMapping("/task_allocation")
    public ModelAndView toTaskAllocation() {
        return new ModelAndView("console/task_allocation");
    }

    @RequestMapping("/task_allocation_complete")
    public ModelAndView toTaskAllocationComplete() {
        return new ModelAndView("console/task_allocation_complete");
    }

    /**
     * ADMINISTRATOR)
     */
    @RequestMapping("/user_management")
    public ModelAndView toUserManagement() {
        return new ModelAndView("console/user_management");
    }

    /**
     * ADMINI   STRATOR)
     */
    @RequestMapping("/camera_management")
    public ModelAndView toCameraManagement() {
        return new ModelAndView("console/camera_management");
    }


    /**
     * ADMINISTRATOR)
     */
    @RequestMapping("/server_management")
    public ModelAndView toServerManagement() {
        return new ModelAndView("console/server_management");
    }

    /**
     * ADMINISTRATOR)
     */
    @RequestMapping("/employee_management")
    public ModelAndView toEmployeeManagement() {
        return new ModelAndView("console/employee_management");
    }


    @RequestMapping("/visitor_management")
    public ModelAndView toVisitorManagement() {
        return new ModelAndView("console/visitor_management");
    }

    @RequestMapping("/attendance_management")
    public ModelAndView toAttendanceManagement() {
        return new ModelAndView("console/attendance_management");
    }

    @RequestMapping("/camera_info")
    public ModelAndView toCameraInfo() {
        return new ModelAndView("console/camera_info");
    }

    @RequestMapping("/camera_info_hour")
    public ModelAndView toCameraInfoHour() {
        return new ModelAndView("console/camera_info_hour");
    }

    @RequestMapping("/trace_info")
    public ModelAndView toTraceInfo() {
        return new ModelAndView("console/trace_info");
    }

    @RequestMapping("/server_info")
    public ModelAndView toServerInfo() {
        return new ModelAndView("console/server_info");
    }

    @RequestMapping("/contacts_management")
    public ModelAndView toContacts() {
        return new ModelAndView("console/contacts_management");
    }

    @RequestMapping("/contacts_config")
    public ModelAndView toContactsConfig() {
        return new ModelAndView("console/contacts_config");
    }

    @RequestMapping("/camera_location")
    public ModelAndView toCameraLocation() {
        return new ModelAndView("console/camera_location");
    }

    @RequestMapping("/face_detect")
    public ModelAndView toFaceDetect() {
        return new ModelAndView("console/face_detect");
    }

    @RequestMapping("/test")
    public ModelAndView toTest() {
        return new ModelAndView("console/test");
    }


    @RequestMapping("/RTSP")
    public ModelAndView toRTS() {
        return new ModelAndView("console/RTSP");
    }



    @RequestMapping("/visitorRecord_management")
    public ModelAndView toVisitorRecord() {
        return new ModelAndView("console/visitorRecord_management");
    }

    /**
     * CUSTOMER
     */
    @RequestMapping("/task_schedule_tracking")
    public ModelAndView toTaskScheduleTracking() {
        return new ModelAndView("console/task_schedule_tracking");
    }

    @RequestMapping("/task_schedule_tracking_complete")
    public ModelAndView toTaskScheduleTrackingComplete() {
        return new ModelAndView("console/task_schedule_tracking_complete");
    }

    /**
     * SUPPLIER
     */

    @RequestMapping("/task_management_complete")
    public ModelAndView toTaskManagementComplete() {
        return new ModelAndView("console/task_management_complete");
    }

    @RequestMapping("/auto_task_assignment")
    public ModelAndView toAutoTaskAssignment() {
        return new ModelAndView("console/auto_task_assignment");
    }


    @RequestMapping("/face_upload")
    public ModelAndView toupload_picture() {
        return new ModelAndView("console/face_upload");
    }

    @RequestMapping("/face_register")
    public ModelAndView toface_register() {
        return new ModelAndView("console/face_register");
    }


    @RequestMapping("/face_task")
    public ModelAndView toface_task() {
        return new ModelAndView("console/face_task");
    }

    @RequestMapping("/face_config")
    public ModelAndView toface_config() {
        return new ModelAndView("console/face_config");
    }

    @RequestMapping("/charts")
    public ModelAndView tocharts() {
        return new ModelAndView("console/charts");
    }

    @RequestMapping("/chart")
    public ModelAndView tochart() {
        return new ModelAndView("console/chart");
    }

    /**
     * Test
     * */
    @RequestMapping("/server_test")
    public ModelAndView toServer_test() {
        return new ModelAndView("console/server_test");
    }
}
