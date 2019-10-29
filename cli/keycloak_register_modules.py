#!/usr/bin/env python2

"""
Usage:
    ./keycloak_register_modules.py keycloak-phone-authenticator-yuntongxun-sms-config.cli $JBOSS_HOME
"""

import sys
from lxml import etree
import re

cfg = sys.argv[1]
jboss_home = sys.argv[2]
jboss_home = jboss_home if jboss_home[-1] == '/' else jboss_home + '/'

cfg_files = [
    'standalone/configuration/standalone.xml',
    'standalone/configuration/standalone-ha.xml'
]

modules = []
with open(cfg) as file:
    for line in file.readlines():
        modules.append(re.findall('value=(.*?)\)', line)[0])

for file_name in cfg_files:
    root = etree.parse(jboss_home + file_name).getroot()
    profile = root.find('{urn:jboss:domain:10.0}profile')
    subsystem = profile.find('{urn:jboss:domain:keycloak-server:1.1}subsystem')
    providers = subsystem.find('{urn:jboss:domain:keycloak-server:1.1}providers')
    for module in modules:
        etree.SubElement(providers, 'provider').text = module
    with open(file_name.split('/')[-1], 'wb') as file:
        file.write("<?xml version='1.0' encoding='UTF-8'?>\n\n".encode('utf-8'))
        file.write(etree.tostring(root, pretty_print=True))
