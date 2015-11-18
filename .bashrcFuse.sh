echo "**************************************************************"
echo "* Welcome ${GREEN}}RooT{${END} or $USER"
echo "* l - list"
echo "* u - update"
echo "* ss - log:tail"
echo "* ttt - install Tetra bundles"
echo "**************************************************************"
ss = {log:tail $args} ;
u = {update $args} ;
l = {list} ;
ttt = {
features:addurl mvn:com.strikersoft.tetra/tetra-integration/0.0.11/xml/features
features:addurl mvn:com.strikersoft.tetra/tetra-domain/1.0.0/xml/features
features:addurl mvn:com.strikersoft.tetra/tetra-storage/1.0.0/xml/features
features:addurl mvn:com.strikersoft.tetra/tetra-common-rest/1.0.0/xml/features
features:addurl mvn:com.strikersoft.tetra/tetra-template/1.0.0/xml/features
features:addurl mvn:com.strikersoft.tetra/tetra-auth/1.0.0/xml/features
features:addurl mvn:com.strikersoft.tetra/tetra-rest/1.0.0/xml/features
features:addurl mvn:com.strikersoft.tetra/tetra-master-db/1.0.0/xml/features
features:addurl mvn:com.strikersoft.tetra.water/tetra-water-adaptation/1.0.0/xml/features
features:addurl mvn:com.strikersoft.tetra.water/tetra-water-processor/1.0.0/xml/features
features:addurl mvn:com.strikersoft.tetra.hs/tetra-hs-processor/1.0.0/xml/features
features:addurl mvn:com.strikersoft.tetra.custom/tetra-custom-processor/1.0.0/xml/features
features:addurl mvn:com.strikersoft.tetra/tetra-email/1.0.0/xml/features
features:addurl mvn:com.strikersoft.tetra/tetra-print-report/1.0.0/xml/features
features:addurl mvn:com.strikersoft.tetra/tetra-audit-trail/1.0.0/xml/features
features:addurl mvn:com.strikersoft.tetra/tetra-pops-workflow/1.0.0/xml/features
features:addurl mvn:com.strikersoft.tetra/tetra-prism/1.0.0/xml/features
features:addurl mvn:com.strikersoft.tetra/tetra-karaf/1.0.0/xml/features
features:install tetra-integration
features:install tetra-domain
features:install tetra-storage
features:install tetra-common-rest
features:install tetra-template
features:install tetra-auth
features:install tetra-rest
features:install tetra-master-db
features:install tetra-water-processor
features:install tetra-hs-processor
features:install tetra-custom-processor
features:install tetra-email
features:install tetra-print-report
features:install tetra-audit-trail
features:install tetra-pops-workflow
features:install tetra-prism
} ;
