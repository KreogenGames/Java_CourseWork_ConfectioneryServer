package ConfectioneryApplicationServer.services;

import ConfectioneryApplicationServer.models.ShopCart;
import lombok.RequiredArgsConstructor;
import javax.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ConfectioneryApplicationServer.output.Register;
import ConfectioneryApplicationServer.output.UserAlreadyExistsException;
import ConfectioneryApplicationServer.models.User;
import ConfectioneryApplicationServer.models.UserRealize;
import ConfectioneryApplicationServer.repositories.UserRepository;
import ConfectioneryApplicationServer.models.ShopCart;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private ShopCart shopCart;

    @Transactional
    public void addNewUser(Register request) throws UserAlreadyExistsException {
        String userName = request.getUserName();
        if (userExists(userName)) {
            throw new UserAlreadyExistsException(userName);
        }

        shopCart = new ShopCart();
        User user = new User();
        user.setUserName(userName);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    @Transactional(Transactional.TxType.MANDATORY)
    public boolean userExists(String userName) {
        return userRepository.findByUsername(userName).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not found: " + userName)
                );
        return new UserRealize(user);
    }
}