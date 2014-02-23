<#assign staticPath="/static">
<!DOCTYPE html>
<html lang="en">
<head>
    <!--
        Charisma v1.0.0

        Copyright 2012 Muhammad Usman
        Licensed under the Apache License v2.0
        http://www.apache.org/licenses/LICENSE-2.0

        http://usman.it
        http://twitter.com/halalit_usman
    -->
    <meta charset="utf-8">
    <title>Free HTML5 Bootstrap Admin Template</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
    <meta name="author" content="Muhammad Usman">

    <!-- The styles -->
    <link id="bs-css" href="${staticPath}/css/bootstrap-cerulean.css" rel="stylesheet">
    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }
    </style>
    <link href="${staticPath}/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="${staticPath}/css/charisma-app.css" rel="stylesheet">
    <link href="${staticPath}/css/jquery-ui-1.8.21.custom.css" rel="stylesheet">
    <link href='${staticPath}/css/fullcalendar.css' rel='stylesheet'>
    <link href='${staticPath}/css/fullcalendar.print.css' rel='stylesheet'  media='print'>
    <link href='${staticPath}/css/chosen.css' rel='stylesheet'>
    <link href='${staticPath}/css/uniform.default.css' rel='stylesheet'>
    <link href='${staticPath}/css/colorbox.css' rel='stylesheet'>
    <link href='${staticPath}/css/jquery.cleditor.css' rel='stylesheet'>
    <link href='${staticPath}/css/jquery.noty.css' rel='stylesheet'>
    <link href='${staticPath}/css/noty_theme_default.css' rel='stylesheet'>
    <link href='${staticPath}/css/elfinder.min.css' rel='stylesheet'>
    <link href='${staticPath}/css/elfinder.theme.css' rel='stylesheet'>
    <link href='${staticPath}/css/jquery.iphone.toggle.css' rel='stylesheet'>
    <link href='${staticPath}/css/opa-icons.css' rel='stylesheet'>
    <link href='${staticPath}/css/uploadify.css' rel='stylesheet'>

    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- The fav icon -->
    <link rel="shortcut icon" href="/favicon.ico">

</head>

<body>
<!-- topbar starts -->
<div class="navbar">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="index.html"> <img alt="Charisma Logo" src="${staticPath}/img/logo20.png" /> <span>Charisma</span></a>

            <!-- theme selector starts -->
            <div class="btn-group pull-right theme-container" >
                <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="icon-tint"></i><span class="hidden-phone"> Change Theme / Skin</span>
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu" id="themes">
                    <li><a data-value="classic" href="#"><i class="icon-blank"></i> Classic</a></li>
                    <li><a data-value="cerulean" href="#"><i class="icon-blank"></i> Cerulean</a></li>
                    <li><a data-value="cyborg" href="#"><i class="icon-blank"></i> Cyborg</a></li>
                    <li><a data-value="redy" href="#"><i class="icon-blank"></i> Redy</a></li>
                    <li><a data-value="journal" href="#"><i class="icon-blank"></i> Journal</a></li>
                    <li><a data-value="simplex" href="#"><i class="icon-blank"></i> Simplex</a></li>
                    <li><a data-value="slate" href="#"><i class="icon-blank"></i> Slate</a></li>
                    <li><a data-value="spacelab" href="#"><i class="icon-blank"></i> Spacelab</a></li>
                    <li><a data-value="united" href="#"><i class="icon-blank"></i> United</a></li>
                </ul>
            </div>
            <!-- theme selector ends -->

            <!-- user dropdown starts -->
            <div class="btn-group pull-right" >
                <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="icon-user"></i><span class="hidden-phone"> admin</span>
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="#">Profile</a></li>
                    <li class="divider"></li>
                    <li><a href="login.html">Logout</a></li>
                </ul>
            </div>
            <!-- user dropdown ends -->

            <div class="top-nav nav-collapse">
                <ul class="nav">
                    <li><a href="#">Visit Site</a></li>
                    <li>
                        <form class="navbar-search pull-left">
                            <input placeholder="Search" class="search-query span2" name="query" type="text">
                        </form>
                    </li>
                </ul>
            </div><!--/.nav-collapse -->
        </div>
    </div>
</div>
<!-- topbar ends -->
<div class="container-fluid">
<div class="row-fluid">

<!-- left menu starts -->
<div class="span2 main-menu-span">
    <div class="well nav-collapse sidebar-nav">
        <ul class="nav nav-tabs nav-stacked main-menu">
            <li class="nav-header hidden-tablet">Main</li>
            <li><a class="ajax-link" href="index.html"><i class="icon-home"></i><span class="hidden-tablet"> Dashboard</span></a></li>
            <li><a class="ajax-link" href="ui.html"><i class="icon-eye-open"></i><span class="hidden-tablet"> UI Features</span></a></li>
            <li><a class="ajax-link" href="form.html"><i class="icon-edit"></i><span class="hidden-tablet"> Forms</span></a></li>
            <li><a class="ajax-link" href="chart.html"><i class="icon-list-alt"></i><span class="hidden-tablet"> Charts</span></a></li>
            <li><a class="ajax-link" href="typography.html"><i class="icon-font"></i><span class="hidden-tablet"> Typography</span></a></li>
            <li><a class="ajax-link" href="gallery.html"><i class="icon-picture"></i><span class="hidden-tablet"> Gallery</span></a></li>
            <li class="nav-header hidden-tablet">Sample Section</li>
            <li><a class="ajax-link" href="table.html"><i class="icon-align-justify"></i><span class="hidden-tablet"> Tables</span></a></li>
            <li><a class="ajax-link" href="calendar.html"><i class="icon-calendar"></i><span class="hidden-tablet"> Calendar</span></a></li>
            <li><a class="ajax-link" href="grid.html"><i class="icon-th"></i><span class="hidden-tablet"> Grid</span></a></li>
            <li><a class="ajax-link" href="file-manager.html"><i class="icon-folder-open"></i><span class="hidden-tablet"> File Manager</span></a></li>
            <li><a href="tour.html"><i class="icon-globe"></i><span class="hidden-tablet"> Tour</span></a></li>
            <li><a class="ajax-link" href="icon.html"><i class="icon-star"></i><span class="hidden-tablet"> Icons</span></a></li>
            <li><a href="error.html"><i class="icon-ban-circle"></i><span class="hidden-tablet"> Error Page</span></a></li>
            <li><a href="login.html"><i class="icon-lock"></i><span class="hidden-tablet"> Login Page</span></a></li>
        </ul>
        <label id="for-is-ajax" class="hidden-tablet" for="is-ajax"><input id="is-ajax" type="checkbox"> Ajax on menu</label>
    </div><!--/.well -->
</div><!--/span-->
<!-- left menu ends -->

<noscript>
    <div class="alert alert-block span10">
        <h4 class="alert-heading">Warning!</h4>
        <p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a> enabled to use this site.</p>
    </div>
</noscript>

<div id="content" class="span10">
<!-- content starts -->


<div>
    <ul class="breadcrumb">
        <li>
            <a href="#">Home</a> <span class="divider">/</span>
        </li>
        <li>
            <a href="#">Dashboard</a>
        </li>
    </ul>
</div>
<div class="sortable row-fluid">
    <a data-rel="tooltip" title="6 new members." class="well span3 top-block" href="#">
        <span class="icon32 icon-red icon-user"></span>
        <div>Total Members</div>
        <div>507</div>
        <span class="notification">6</span>
    </a>

    <a data-rel="tooltip" title="4 new pro members." class="well span3 top-block" href="#">
        <span class="icon32 icon-color icon-star-on"></span>
        <div>Pro Members</div>
        <div>228</div>
        <span class="notification green">4</span>
    </a>

    <a data-rel="tooltip" title="$34 new sales." class="well span3 top-block" href="#">
        <span class="icon32 icon-color icon-cart"></span>
        <div>Sales</div>
        <div>$13320</div>
        <span class="notification yellow">$34</span>
    </a>

    <a data-rel="tooltip" title="12 new messages." class="well span3 top-block" href="#">
        <span class="icon32 icon-color icon-envelope-closed"></span>
        <div>Messages</div>
        <div>25</div>
        <span class="notification red">12</span>
    </a>
</div>

<div class="row-fluid">
    <div class="box span12">
        <div class="box-header well">
            <h2><i class="icon-info-sign"></i> Introduction</h2>
            <div class="box-icon">
                <a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
                <a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
                <a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
            </div>
        </div>
        <div class="box-content">
            <h1>Charisma <small>free, premium quality, responsive, multiple skin admin template.</small></h1>
            <p>Its a live demo of the template. I have created Charisma to ease the repeat work I have to do on my projects. Now I re-use Charisma as a base for my admin panel work and I am sharing it with you :)</p>
            <p><b>All pages in the menu are functional, take a look at all, please share this with your followers.</b></p>

            <p class="center">
                <a href="http://usman.it/free-responsive-admin-template" class="btn btn-large btn-primary"><i class="icon-chevron-left icon-white"></i> Back to article</a>
                <a href="http://usman.it/free-responsive-admin-template" class="btn btn-large"><i class="icon-download-alt"></i> Download Page</a>
            </p>
            <div class="clearfix"></div>
        </div>
    </div>
</div>

<div class="row-fluid sortable">
    <div class="box span4">
        <div class="box-header well">
            <h2><i class="icon-th"></i> Tabs</h2>
            <div class="box-icon">
                <a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
                <a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
                <a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
            </div>
        </div>
        <div class="box-content">
            <ul class="nav nav-tabs" id="myTab">
                <li class="active"><a href="#info">Info</a></li>
                <li><a href="#custom">Custom</a></li>
                <li><a href="#messages">Messages</a></li>
            </ul>

            <div id="myTabContent" class="tab-content">
                <div class="tab-pane active" id="info">
                    <h3>Charisma <small>a fully featued template</small></h3>
                    <p>Its a fully featured, responsive template for your admin panel. Its optimized for tablet and mobile phones. Scan the QR code below to view it in your mobile device.</p> <img alt="QR Code" class="charisma_qr center" src="${staticPath}/img/qrcode136.png" />
                </div>
                <div class="tab-pane" id="custom">
                    <h3>Custom <small>small text</small></h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur bibendum ornare dolor.</p>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur bibendum ornare dolor, quis ullamcorper ligula sodales at. Nulla tellus elit, varius non commodo eget, mattis vel eros. In sed ornare nulla. Donec consectetur, velit a pharetra ultricies, diam lorem lacinia risus, ac commodo orci erat eu massa. Sed sit amet nulla ipsum. Donec felis mauris, vulputate sed tempor at, aliquam a ligula. Pellentesque non pulvinar nisi.</p>
                </div>
                <div class="tab-pane" id="messages">
                    <h3>Messages <small>small text</small></h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur bibendum ornare dolor, quis ullamcorper ligula sodales at. Nulla tellus elit, varius non commodo eget, mattis vel eros. In sed ornare nulla. Donec consectetur, velit a pharetra ultricies, diam lorem lacinia risus, ac commodo orci erat eu massa. Sed sit amet nulla ipsum. Donec felis mauris, vulputate sed tempor at, aliquam a ligula. Pellentesque non pulvinar nisi.</p>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur bibendum ornare dolor.</p>
                </div>
            </div>
        </div>
    </div><!--/span-->

    <div class="box span4">
        <div class="box-header well" data-original-title>
            <h2><i class="icon-user"></i> Member Activity</h2>
            <div class="box-icon">
                <a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
                <a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
            </div>
        </div>
        <div class="box-content">
            <div class="box-content">
                <ul class="dashboard-list">
                    <li>
                        <a href="#">
                            <img class="dashboard-avatar" alt="Usman" src="http://www.gravatar.com/avatar/f0ea51fa1e4fae92608d8affee12f67b.png?s=50"></a>
                        <strong>Name:</strong> <a href="#">Usman
                    </a><br>
                        <strong>Since:</strong> 17/05/2012<br>
                        <strong>Status:</strong> <span class="label label-success">Approved</span>
                    </li>
                    <li>
                        <a href="#">
                            <img class="dashboard-avatar" alt="Sheikh Heera" src="http://www.gravatar.com/avatar/3232415a0380253cfffe19163d04acab.png?s=50"></a>
                        <strong>Name:</strong> <a href="#">Sheikh Heera
                    </a><br>
                        <strong>Since:</strong> 17/05/2012<br>
                        <strong>Status:</strong> <span class="label label-warning">Pending</span>
                    </li>
                    <li>
                        <a href="#">
                            <img class="dashboard-avatar" alt="Abdullah" src="http://www.gravatar.com/avatar/46056f772bde7c536e2086004e300a04.png?s=50"></a>
                        <strong>Name:</strong> <a href="#">Abdullah
                    </a><br>
                        <strong>Since:</strong> 25/05/2012<br>
                        <strong>Status:</strong> <span class="label label-important">Banned</span>
                    </li>
                    <li>
                        <a href="#">
                            <img class="dashboard-avatar" alt="Saruar Ahmed" src="http://www.gravatar.com/avatar/564e1bb274c074dc4f6823af229d9dbb.png?s=50"></a>
                        <strong>Name:</strong> <a href="#">Saruar Ahmed
                    </a><br>
                        <strong>Since:</strong> 17/05/2012<br>
                        <strong>Status:</strong> <span class="label label-info">Updates</span>
                    </li>
                </ul>
            </div>
        </div>
    </div><!--/span-->

    <div class="box span4">
        <div class="box-header well" data-original-title>
            <h2><i class="icon-list-alt"></i> Realtime Traffic</h2>
            <div class="box-icon">
                <a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
                <a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
            </div>
        </div>
        <div class="box-content">
            <div id="realtimechart" style="height:190px;"></div>
            <p class="clearfix">You can update a chart periodically to get a real-time effect by using a timer to insert the new data in the plot and redraw it.</p>
            <p>Time between updates: <input id="updateInterval" type="text" value="" style="text-align: right; width:5em"> milliseconds</p>
        </div>
    </div><!--/span-->
</div><!--/row-->

<div class="row-fluid sortable">
    <div class="box span4">
        <div class="box-header well" data-original-title>
            <h2><i class="icon-list"></i> Buttons</h2>
            <div class="box-icon">
                <a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
                <a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
                <a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
            </div>
        </div>
        <div class="box-content buttons">
            <p class="btn-group">
                <button class="btn">Left</button>
                <button class="btn">Middle</button>
                <button class="btn">Right</button>
            </p>
            <p>
                <button class="btn btn-small"><i class="icon-star"></i> Icon button</button>
                <button class="btn btn-small btn-primary">Small button</button>
                <button class="btn btn-small btn-danger">Small button</button>
            </p>
            <p>
                <button class="btn btn-small btn-warning">Small button</button>
                <button class="btn btn-small btn-success">Small button</button>
                <button class="btn btn-small btn-info">Small button</button>
            </p>
            <p>
                <button class="btn btn-small btn-inverse">Small button</button>
                <button class="btn btn-large btn-primary btn-round">Round button</button>
                <button class="btn btn-large btn-round"><i class="icon-ok"></i></button>
                <button class="btn btn-primary"><i class="icon-edit icon-white"></i></button>
            </p>
            <p>
                <button class="btn btn-mini">Mini button</button>
                <button class="btn btn-mini btn-primary">Mini button</button>
                <button class="btn btn-mini btn-danger">Mini button</button>
                <button class="btn btn-mini btn-warning">Mini button</button>
            </p>
            <p>
                <button class="btn btn-mini btn-info">Mini button</button>
                <button class="btn btn-mini btn-success">Mini button</button>
                <button class="btn btn-mini btn-inverse">Mini button</button>
            </p>
        </div>
    </div><!--/span-->

    <div class="box span4">
        <div class="box-header well" data-original-title>
            <h2><i class="icon-list"></i> Buttons</h2>
            <div class="box-icon">
                <a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
                <a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
                <a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
            </div>
        </div>
        <div class="box-content  buttons">
            <p>
                <button class="btn btn-large">Large button</button>
                <button class="btn btn-large btn-primary">Large button</button>
            </p>
            <p>
                <button class="btn btn-large btn-danger">Large button</button>
                <button class="btn btn-large btn-warning">Large button</button>
            </p>
            <p>
                <button class="btn btn-large btn-success">Large button</button>
                <button class="btn btn-large btn-info">Large button</button>
            </p>
            <p>
                <button class="btn btn-large btn-inverse">Large button</button>
            </p>
            <div class="btn-group">
                <button class="btn btn-large">Large Dropdown</button>
                <button class="btn btn-large dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></button>
                <ul class="dropdown-menu">
                    <li><a href="#"><i class="icon-star"></i> Action</a></li>
                    <li><a href="#"><i class="icon-tag"></i> Another action</a></li>
                    <li><a href="#"><i class="icon-download-alt"></i> Something else here</a></li>
                    <li class="divider"></li>
                    <li><a href="#"><i class="icon-tint"></i> Separated link</a></li>
                </ul>
            </div>

        </div>
    </div><!--/span-->

    <div class="box span4">
        <div class="box-header well" data-original-title>
            <h2><i class="icon-list"></i> Weekly Stat</h2>
            <div class="box-icon">
                <a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
                <a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
                <a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
            </div>
        </div>
        <div class="box-content">
            <ul class="dashboard-list">
                <li>
                    <a href="#">
                        <i class="icon-arrow-up"></i>
                        <span class="green">92</span>
                        New Comments
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="icon-arrow-down"></i>
                        <span class="red">15</span>
                        New Registrations
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="icon-minus"></i>
                        <span class="blue">36</span>
                        New Articles
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="icon-comment"></i>
                        <span class="yellow">45</span>
                        User reviews
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="icon-arrow-up"></i>
                        <span class="green">112</span>
                        New Comments
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="icon-arrow-down"></i>
                        <span class="red">31</span>
                        New Registrations
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="icon-minus"></i>
                        <span class="blue">93</span>
                        New Articles
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="icon-comment"></i>
                        <span class="yellow">254</span>
                        User reviews
                    </a>
                </li>
            </ul>
        </div>
    </div><!--/span-->
</div><!--/row-->




<!-- content ends -->
</div><!--/#content.span10-->
</div><!--/fluid-row-->

<hr>

<div class="modal hide fade" id="myModal">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">×</button>
        <h3>Settings</h3>
    </div>
    <div class="modal-body">
        <p>Here settings can be configured...</p>
    </div>
    <div class="modal-footer">
        <a href="#" class="btn" data-dismiss="modal">Close</a>
        <a href="#" class="btn btn-primary">Save changes</a>
    </div>
</div>

<footer>
    <p class="pull-left">&copy; <a href="http://usman.it" target="_blank">Muhammad Usman</a> 2012</p>
    <p class="pull-right">Powered by: <a href="http://usman.it/free-responsive-admin-template">Charisma</a></p>
</footer>

</div><!--/.fluid-container-->

<!-- external javascript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->

<!-- jQuery -->
<script src="${staticPath}/js/jquery-1.7.2.min.js"></script>
<!-- jQuery UI -->
<script src="${staticPath}/js/jquery-ui-1.8.21.custom.min.js"></script>
<!-- transition / effect library -->
<script src="${staticPath}/js/bootstrap-transition.js"></script>
<!-- alert enhancer library -->
<script src="${staticPath}/js/bootstrap-alert.js"></script>
<!-- modal / dialog library -->
<script src="${staticPath}/js/bootstrap-modal.js"></script>
<!-- custom dropdown library -->
<script src="${staticPath}/js/bootstrap-dropdown.js"></script>
<!-- scrolspy library -->
<script src="${staticPath}/js/bootstrap-scrollspy.js"></script>
<!-- library for creating tabs -->
<script src="${staticPath}/js/bootstrap-tab.js"></script>
<!-- library for advanced tooltip -->
<script src="${staticPath}/js/bootstrap-tooltip.js"></script>
<!-- popover effect library -->
<script src="${staticPath}/js/bootstrap-popover.js"></script>
<!-- button enhancer library -->
<script src="${staticPath}/js/bootstrap-button.js"></script>
<!-- accordion library (optional, not used in demo) -->
<script src="${staticPath}/js/bootstrap-collapse.js"></script>
<!-- carousel slideshow library (optional, not used in demo) -->
<script src="${staticPath}/js/bootstrap-carousel.js"></script>
<!-- autocomplete library -->
<script src="${staticPath}/js/bootstrap-typeahead.js"></script>
<!-- tour library -->
<script src="${staticPath}/js/bootstrap-tour.js"></script>
<!-- library for cookie management -->
<script src="${staticPath}/js/jquery.cookie.js"></script>
<!-- calander plugin -->
<script src='${staticPath}/js/fullcalendar.min.js'></script>
<!-- data table plugin -->
<script src='${staticPath}/js/jquery.dataTables.min.js'></script>

<!-- chart libraries start -->
<script src="${staticPath}/js/excanvas.js"></script>
<script src="${staticPath}/js/jquery.flot.min.js"></script>
<script src="${staticPath}/js/jquery.flot.pie.min.js"></script>
<script src="${staticPath}/js/jquery.flot.stack.js"></script>
<script src="${staticPath}/js/jquery.flot.resize.min.js"></script>
<!-- chart libraries end -->

<!-- select or dropdown enhancer -->
<script src="${staticPath}/js/jquery.chosen.min.js"></script>
<!-- checkbox, radio, and file input styler -->
<script src="${staticPath}/js/jquery.uniform.min.js"></script>
<!-- plugin for gallery image view -->
<script src="${staticPath}/js/jquery.colorbox.min.js"></script>
<!-- rich text editor library -->
<script src="${staticPath}/js/jquery.cleditor.min.js"></script>
<!-- notification plugin -->
<script src="${staticPath}/js/jquery.noty.js"></script>
<!-- file manager library -->
<script src="${staticPath}/js/jquery.elfinder.min.js"></script>
<!-- star rating plugin -->
<script src="${staticPath}/js/jquery.raty.min.js"></script>
<!-- for iOS style toggle switch -->
<script src="${staticPath}/js/jquery.iphone.toggle.js"></script>
<!-- autogrowing textarea plugin -->
<script src="${staticPath}/js/jquery.autogrow-textarea.js"></script>
<!-- multiple file upload plugin -->
<script src="${staticPath}/js/jquery.uploadify-3.1.min.js"></script>
<!-- history.js for cross-browser state change on ajax -->
<script src="${staticPath}/js/jquery.history.js"></script>
<!-- application script for Charisma demo -->
<script src="${staticPath}/js/charisma.js"></script>


</body>
</html>
