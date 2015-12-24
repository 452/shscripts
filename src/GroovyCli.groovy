/*#!/usr/bin/groovy
def cli = new CliBuilder(usage:'ls', header: '\nAvailable options (use -h for help):\n', footer: '\nInformation provided via above options is used to generate printed string.\n')
cli.a('display all files')
cli.l('use a long listing format')
cli.t('sort by modification time')
//cli.s(longOpt: 'state', 'State Name', args: 1, required: true)
//z(longOpt: 'zip', 'Zip Codes (separated by comma)', required: true, args: Option.UNLIMITED_VALUES, valueSeparator: ',')
cli.h('help info')
//cli.usage()
def opt = cli.parse(args)
println("Hello " + test)
//if (options.s) println options.s
if(opt.h) cli.usage()*/