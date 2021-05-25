package ConfectioneryApplicationServer.services;

import ConfectioneryApplicationServer.models.ShopCart;
import lombok.RequiredArgsConstructor;
import javax.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ConfectioneryApplicationServer.output.SignUpRequest;
import ConfectioneryApplicationServer.output.UserAlreadyExistsException;
import ConfectioneryApplicationServer.models.User;
import ConfectioneryApplicationServer.models.UserPrincipal;
import ConfectioneryApplicationServer.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void addNewUser(SignUpRequest request) throws UserAlreadyExistsException {
        String username = request.getUsername();
        if (userExists(username)) {
            throw new UserAlreadyExistsException(username);
        }

        ShopCart shopCart = new ShopCart();
        User user = new User();
        /*user.setLastName(request.getLastName());
        user.setFirstName(request.getFirstName());
        if(request.getMiddleName() != null){
            user.setMiddleName(request.getMiddleName());
        }*/
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        shopCart.setUser(user);
        //Проверить на правильность, тк не уверен в корректности реализации связи м/ж корзиной и пользователем
        //user.setShopCart(shopCart);
        userRepository.save(user);
    }

    @Transactional(Transactional.TxType.MANDATORY)
    public boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not found: " + username)
                );
        return new UserPrincipal(user);
    }
}