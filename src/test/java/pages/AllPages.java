package pages;

public class AllPages {

    public AllPages(){

    }
    private HomePage homePage;
    private LandingPage landingPage;
    private LoginPage loginPage;
    private SearchResultsPage searchResultsPage;


    public HomePage homePage(){
        if(homePage == null){
            homePage = new HomePage();
        }
        return homePage;
    }

    public LandingPage landingPage(){
        if(landingPage == null){
            landingPage = new LandingPage();
        }
        return landingPage;
    }

    public LoginPage loginPage(){
        if(loginPage == null){
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public SearchResultsPage searchResultsPage(){
        if(searchResultsPage == null){
            searchResultsPage = new SearchResultsPage();
        }
        return searchResultsPage;
    }

}
