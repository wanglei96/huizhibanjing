package BRP.model;

import BRP.data.ActivityServiceTimeMaster;
import BRP.data.ViewMemberCard;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import strosoft.app.common.MySqlConditionBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityServiceTimeManager extends ActivityServiceTimeMaster {
    private static ActivityServiceTimeManager _instance;

    private ActivityServiceTimeManager() {
        this.setTableName("activity_service_time");
        this.setTypeName("ActivityServiceTime");
    }

    public static ActivityServiceTimeManager getInstance() {
        if (_instance == null) {
            _instance = new ActivityServiceTimeManager();
        }
        return _instance;
    }

}