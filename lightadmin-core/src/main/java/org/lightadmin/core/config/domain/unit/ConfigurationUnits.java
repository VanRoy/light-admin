package org.lightadmin.core.config.domain.unit;

import com.google.common.collect.Sets;
import org.lightadmin.core.config.bootstrap.parsing.configuration.DomainConfigurationUnitType;
import org.lightadmin.core.config.domain.configuration.EntityMetadataConfigurationUnit;
import org.lightadmin.core.config.domain.context.ScreenContextConfigurationUnit;
import org.lightadmin.core.config.domain.filter.FiltersConfigurationUnit;
import org.lightadmin.core.config.domain.fragment.ListViewConfigurationUnit;
import org.lightadmin.core.config.domain.scope.ScopesConfigurationUnit;
import org.lightadmin.core.config.domain.show.ShowViewConfigurationUnit;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

import static com.google.common.collect.Sets.newLinkedHashSet;

public class ConfigurationUnits implements Iterable<ConfigurationUnit>, Serializable {

	private final Class<?> domainType;

	private final Set<ConfigurationUnit> configurationUnits;

	public ConfigurationUnits( Class<?> domainType, ConfigurationUnit... configurationUnits ) {
		this( domainType, Sets.<ConfigurationUnit>newLinkedHashSet( Arrays.asList( configurationUnits ) ) );
	}

	public ConfigurationUnits( Class<?> domainType, final Set<ConfigurationUnit> configurationUnits ) {
		Assert.notNull( domainType );

		this.domainType = domainType;
		this.configurationUnits = newLinkedHashSet( configurationUnits );
	}

	@Override
	public Iterator<ConfigurationUnit> iterator() {
		return configurationUnits.iterator();
	}

	public Class<?> getDomainType() {
		return domainType;
	}

	public ConfigurationUnit forDomainUnitType( DomainConfigurationUnitType domainConfigurationUnitType ) {
		for ( ConfigurationUnit configurationUnit : configurationUnits ) {
			if ( configurationUnit.getDomainConfigurationUnitType().equals( domainConfigurationUnitType ) ) {
				return configurationUnit;
			}
		}
		return null;
	}

	public FiltersConfigurationUnit getFilters() {
		return ( FiltersConfigurationUnit ) forDomainUnitType( DomainConfigurationUnitType.FILTERS );
	}

	public ScopesConfigurationUnit getScopes() {
		return ( ScopesConfigurationUnit ) forDomainUnitType( DomainConfigurationUnitType.SCOPES );
	}

	public ListViewConfigurationUnit getListViewFragment() {
		return ( ListViewConfigurationUnit ) forDomainUnitType( DomainConfigurationUnitType.LIST_VIEW );
	}

	public ScreenContextConfigurationUnit getScreenContext() {
		return ( ScreenContextConfigurationUnit ) forDomainUnitType( DomainConfigurationUnitType.SCREEN_CONTEXT );
	}

	public EntityMetadataConfigurationUnit getEntityConfiguration() {
		return ( EntityMetadataConfigurationUnit ) forDomainUnitType( DomainConfigurationUnitType.CONFIGURATION );
	}

	public ShowViewConfigurationUnit getShowViewConfigurationUnit() {
		return ( ShowViewConfigurationUnit ) forDomainUnitType( DomainConfigurationUnitType.SHOW_VIEW );
	}
}