QT -= core

TARGET = openssl
TEMPLATE = lib

CONFIG += staticlib warn_on

INCLUDEPATH += \
../../../../platform/shared/ruby/include\
../../../../platform/shared\
../../../../platform/shared/common

macx {
  DESTDIR = ../../../../platform/osx/bin/extensions
  OBJECTS_DIR = ../../../../platform/osx/bin/extensions/openssl
  INCLUDEPATH += ../../../../platform/shared/ruby/iphone macosx
  HEADERS += macosx/extconf.h
  INCLUDEPATH += /Users/MOHUS/tauplatform/openssl-1.0.2/include
}
win32 {
  DESTDIR = ../../../../platform/win32/bin/extensions
  OBJECTS_DIR = ../../../../platform/win32/bin/extensions/openssl
  DEFINES += WIN32 _WINDOWS _LIB _UNICODE UNICODE
  Debug {
    DEFINES += _DEBUG DEBUG
  }
  Release {
    DEFINES += _NDEBUG NDEBUG
  }
  INCLUDEPATH += ../../../../platform/shared/ruby/win32\
../../openssl.so/ext/win32/include\
windows
  HEADERS += windows/extconf.h
}

DEFINES += RHODES_QT_PLATFORM _XOPEN_SOURCE _DARWIN_C_SOURCE

!isEmpty(RHOSIMULATOR_BUILD) {
  DEFINES += RHODES_EMULATOR
}

!win32 {
  QMAKE_CFLAGS_WARN_ON += -Wno-extra -Wno-unused -Wno-sign-compare -Wno-format -Wno-parentheses
  QMAKE_CXXFLAGS_WARN_ON += -Wno-extra -Wno-unused -Wno-sign-compare -Wno-format -Wno-parentheses
  # QMAKE_CFLAGS += -fvisibility=hidden
  # QMAKE_CXXFLAGS += -fvisibility=hidden
}
win32 {
  QMAKE_CFLAGS_WARN_ON += /wd4996 /wd4100 /wd4005
  QMAKE_CXXFLAGS_WARN_ON += /wd4996 /wd4100 /wd4005
  QMAKE_CFLAGS_RELEASE += /O2
  QMAKE_CXXFLAGS_RELEASE += /O2
}

HEADERS += \
openssl_missing.h\
ossl.h\
ossl_asn1.h\
ossl_bio.h\
ossl_bn.h\
ossl_cipher.h\
ossl_config.h\
ossl_digest.h\
ossl_engine.h\
ossl_hmac.h\
ossl_ns_spki.h\
ossl_ocsp.h\
ossl_pkcs12.h\
ossl_pkcs5.h\
ossl_pkcs7.h\
ossl_pkey.h\
ossl_rand.h\
ossl_ssl.h\
ossl_version.h\
ossl_x509.h\
ruby_missing.h

SOURCES += \
openssl_missing.c\
ossl.c\
ossl_asn1.c\
ossl_bio.c\
ossl_bn.c\
ossl_cipher.c\
ossl_config.c\
ossl_digest.c\
ossl_engine.c\
ossl_hmac.c\
ossl_ns_spki.c\
ossl_ocsp.c\
ossl_pkcs12.c\
ossl_pkcs5.c\
ossl_pkcs7.c\
ossl_pkey.c\
ossl_pkey_dh.c\
ossl_pkey_dsa.c\
ossl_pkey_ec.c\
ossl_pkey_rsa.c\
ossl_rand.c\
ossl_ssl.c\
ossl_ssl_session.c\
ossl_x509.c\
ossl_x509attr.c\
ossl_x509cert.c\
ossl_x509crl.c\
ossl_x509ext.c\
ossl_x509name.c\
ossl_x509req.c\
ossl_x509revoked.c\
ossl_x509store.c
