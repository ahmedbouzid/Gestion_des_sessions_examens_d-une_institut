package com.isima.session.isima.security;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
/*
 * hawka cv  login mazel logout.
 * yar7em weldik ybereklek.
 * ma3melt chay ma3nich fer7an 5ater mouch fehmha :(
 * hhhhh chouf rendez_vous m3a secretaire taw nfehmhalek 
 * hhh mrirgl
 * ne5dmou logout ?
 * mezel 3andek wa9t mahou ? 
 * ui nosf lil nchalah
 * inshallah, w bellehi chnes2lek 3la recherche akili 3amlinha fil accueil hedhika. feha barcha 5edma ? 
 * 3la chnouw recherceh?
 * par exemple nekteb esm matière y5arejli mel BD les données mta3ha, wela enseig kifkif y5arejli les données mta3ou.
 * enajmou ne5dmouha ama yalzm 3la chnouw recherche, 3la ana table , lber9i yet5dm
 * par exempl fil bare de recherche hedhika li fil accueil nekteb ay esm mta3 enseginant(haj mtir) y5arejli grade mta3o w mail m table enseignant.
 * oui fesable
 * faisable*
 * hh yes
 * behi.
 * ne5dmou taw logout
 * d'acc.
 * wela 9bel ne5dmou msg d'erreur mte3 login ki yebda login wela psw ghaltin
 * behi la7dha 5alini n7ot Username et password compatible mn taw ma3neha chn7ot esm l'agent .
 * ok
 * hamdoulileh hawka c'est bon logout :)
 * hamdoullah inshalla rabi ybereklek .
 * amine yalzm fil navbar dzidha fi kol page logout ? 
 * ui k
 * ima heka
 * kima heka
 * akhaw
 * kifech bellehi mafhemthech.
 * ahou exemple
 * 
 * ainsic de suite lil les autres pages.
 * wada7 w barra yar7em weldik ya behi.
 * :) chnouw mazel ?
 * recherche ili 7kitlk 3leha et fiche dee suivie w salamou 3alaykom. + control de saisie
 * controle de saisie lel login ? 
 * nn lil ajout mte3 ay entité feha contrainte
 * d'accord wa9tech tnajem tetfadha marra o5ra
 * w bellehi sama7na ta3ebnek m3ana :D
 * wa9tch a5er date ?
 * hiya soutenance tech le jeudi, ken no5ltou 8odwa 3la fiche de suivie et recherche sinn n5alou 7aja menhem lel weekend.
 * jeudi hetha wela jem3a jeya ?
 * hh le le jeudi hedha ça fait ba3d 8odwa.
 * jeudi jem3a jeya donc ?
 * le ba3d ghodwasotenece technique
 * aa donc yalzm ne5dmouha 9bal ba3d 4odoi ?
 * mafhemtnich, 8odwa e5er nhar fil 5edma 9bel sotenece technique ma3neha ken tnajem tetfadha 8odwa ne5dmou ya recherche ya fiche de suivie.
 * chnouw feha fichier suivie
 * hay ma7abetch tet3adeli 
 * hethaka calendrier ma 3indch 7ata examen
 * w ba3d ?
 * chnouwa a ba3d ?
 * fi beli mich twarini fich suivuie
 * ma7abetch tet3ada 8odwa inshallah nwarihelk taw ilila nkamel na3mel saisie mta3 des examens 
 * ok nchalah 4odoi ba3d moghreb nchlah
 *  d'accord inshallah .
 *  aya salam
 *  salam w sama7na fi ta3bek.
 *  3edi 3edi rabi yjazi nchalah :)
 *  inshalla <3
 *  salam
 *  salam.
 */
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.util.StringUtils;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("Wassim").password("{noop}IsimaIsima").roles("admin");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").authenticated()
		.and().
			formLogin().loginPage("/login")
		.and()
		.logout()
        .logoutSuccessUrl("/login?logout=true")
        .invalidateHttpSession(true)
        .permitAll();
	}
	
	
}
