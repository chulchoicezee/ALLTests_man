LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

#DS1 model/xxx/AndroidManifest.xml by okdal
define all-manifest-files-under
$(patsubst ./%,%, \
  $(shell cd $(LOCAL_PATH)/model ; \
          find $(1) -name "AndroidManifest.xml" -and -not -name ".*") \
 )
endef
#DS1 model/xxx/AndroidManifest.xml by okdal
LOCAL_MANIFEST_FILE := model/$(all-manifest-files-under)

LOCAL_MODULE_TAGS := optional

contacts_common_dir := ../ContactsCommon

src_dirs := src $(contacts_common_dir)/src
# ljsook for BLACK THEME : add res-black
#res_dirs := res-black res $(contacts_common_dir)/res
res_dirs := res $(contacts_common_dir)/res

LOCAL_SRC_FILES := $(call all-java-files-under, $(src_dirs))
LOCAL_RESOURCE_DIR := $(addprefix $(LOCAL_PATH)/, $(res_dirs))

LOCAL_AAPT_FLAGS := \
    --auto-add-overlay \
    --extra-packages com.android.contacts.common

# JB4.1 to JB4.2 by OKDAL
LOCAL_JAVA_LIBRARIES := telephony-common voip-common \
                       mms-common

# pcu_p11337 : [FEATURE_CALLUI] added isp lib.                       
LOCAL_STATIC_JAVA_LIBRARIES := \
    com.android.phone.shared \
    com.android.vcard \
    android-common \
    guava \
    android-support-v13 \
    android-support-v4 \
    android-ex-variablespeed \
    com.pantech.isp \
    
LOCAL_REQUIRED_MODULES := libvariablespeed

LOCAL_PACKAGE_NAME := Contacts
LOCAL_CERTIFICATE := shared
LOCAL_PRIVILEGED_MODULE := true

#LOCAL_PROGUARD_FLAG_FILES := proguard.flags
LOCAL_PROGUARD_ENABLED := disabled

include $(BUILD_PACKAGE)

# Use the folloing include to make our test apk.
include $(call all-makefiles-under,$(LOCAL_PATH))
