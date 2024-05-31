package cloud_google.page.pricing_calculator.available_options;

public enum OperatingSystem {
    FREE__DEBIAN$_CENTOS$_COREOS$_UBUNTU_OR_BYOL,
    PAID__UBUNTU_PRO,
    PAID__WINDOWS_SERVER_2012_R2$_WINDOWS_SERVER_2016$_WINDOWS_SERVER_2019$_WINDOWS_SERVER_2004,
    PAID__RED_HAT_ENTERPRISE_LINUX,
    PAID__RED_HAT_ENTERPRISE_LINUX_FOR_SAP_WITH_HA_AND_UPDATE_SERVICES,
    PAID__SLES,
    PAID__SLES_12_FOR_SAP,
    PAID__SLES_15_FOR_SAP,
    PAID__SQL_SERVER_STANDARD,
    PAID__SQL_SERVER_WEB_2012,
    PAID__SQL_SERVER_ENTERPRISE;

    @Override
    public String toString() {
        return super.toString()
                .replace("__", ": ")
                .replace("_", " ")
                .replace("$", ",")
                .toLowerCase();
    }
}
